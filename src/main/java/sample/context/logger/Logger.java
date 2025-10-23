package sample.context.logger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDateTime;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sample.context.exception.ServiceException;
import sample.dto.ResultDto;
import sample.dto.request.log.LogWriteDto;
import sample.model.Log;
import sample.repository.LogRepository;

/**
 * <pre>
 * Loggerアスペクトクラス
 * 実行処理のログを登録します。
 * </pre>
 */
@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class Logger {
    // DI
    private final LogRepository logRepository;

    /**
     * <pre>
     * ログ出力アノテーション
     * メソッドに付与することで、そのメソッドの実行ログを出力します。
     * </pre>
     */
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Loggable {
        String value() default "";

        String category() default "";
    }

    /**
     * <pre>
     * ログ先行書込み処理
     * 実行処理の開始時にログを登録します。
     * </pre>
     * 
     * @param loggable
     */
    @Before("@annotation(loggable)")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void startWriteLog(JoinPoint joinPoint, Loggable loggable) {
        // TODO: Spring Securityの導入
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String auth = "UNKNOWN";
        String executorId = auth != null ? auth : "UNKNOWN";

        LogWriteDto dto = new LogWriteDto();
        dto.setExecutorId(executorId);
        dto.setProcessName(joinPoint.getSignature().getName());
        dto.setLogType(Log.LogType.PROCESSING.getCode());
        if (loggable.category().equals("API")) {
            // API処理
            dto.setCategory(Log.Category.API.getCode());
        } else if (loggable.category().equals("BATCH")) {
            // バッチ処理
            dto.setCategory(Log.Category.BATCH.getCode());
        }

        try {
            // ログ先行書込み
            long logId = logRepository.startWriteLog(dto);
            LoggerContext.setLogId(logId);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * <pre>
     * ログ完了書込み処理
     * 実行処理の終了時にログを登録します。
     * </pre>
     * 
     * @param loggable
     * @param result   メソッドの戻り値
     */
    @AfterReturning(value = "@annotation(loggable)", returning = "result")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void endWriteLog(JoinPoint joinPoint, Loggable loggable, ResultDto result) {
        LogWriteDto dto = new LogWriteDto();
        dto.setLogId(LoggerContext.getLogId());
        if (result.getResult() == ResultDto.ResultType.SUCCESS) {
            dto.setLogType(Log.LogType.SUCCESS.getCode());
        } else {
            dto.setLogType(Log.LogType.WARN.getCode());
            dto.setMessage(result.getMessage());
        }
        dto.setExecutedAt(LocalDateTime.now());

        try {
            // ログ完了書込み
            logRepository.endWriteLog(dto);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * <pre>
     * ログ例外書込み処理
     * 実行処理で例外が発生した場合にログを登録します。
     * </pre>
     * 
     * @param loggable
     * @param ex       発生した例外
     */
    @AfterThrowing(value = "@annotation(loggable)", throwing = "ex")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void endWriteLog(JoinPoint joinPoint, Loggable loggable, ServiceException ex) {
        LogWriteDto dto = new LogWriteDto();
        dto.setLogId(LoggerContext.getLogId());
        dto.setLogType(Log.LogType.ERROR.getCode());
        dto.setMessage(ex.getMessage());
        dto.setExecutedAt(LocalDateTime.now());

        try {
            // ログ完了書込み
            logRepository.endWriteLog(dto);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
