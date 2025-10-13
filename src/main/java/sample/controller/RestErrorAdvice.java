package sample.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sample.context.constant.error.Error;
import sample.context.util.Message;
import sample.dto.ErrorDto;

/**
 * <pre>
 * REST APIのエラー処理を行うクラス
 * </pre>
 */
@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class RestErrorAdvice {
    // DI
    private final Message message;

    /**
     * <pre>
     * バリデーションエラーを処理するメソッド
     * バリデーションエラーが発生した場合に呼び出され、エラーメッセージをMap形式で返します。
     * status: 400 Bad Request
     * </pre>
     * 
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * <pre>
     * 必須パラメータが不足している場合の例外を処理するメソッド
     * 必須パラメータが不足している場合に呼び出され、エラーメッセージを返します。
     * status: 400 Bad Request
     * </pre>
     * 
     * @param ex
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorDto> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException ex) {
        ErrorDto error = new ErrorDto();
        error.setErrorCode(Error.BAD_REQUEST_GLOBAL);
        error.setErrorMessage(message.get("error.global.bad_request"));

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * <pre>
     * パラメータの型が一致しない場合の例外を処理するメソッド
     * パラメータの型が一致しない場合に呼び出され、エラーメッセージを返します。
     * status: 400 Bad Request
     * </pre>
     * 
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorDto> handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        ErrorDto error = new ErrorDto();
        error.setErrorCode(Error.BAD_REQUEST_GLOBAL);
        error.setErrorMessage(message.get("error.global.bad_request"));

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * <pre>
     * リソースが見つからない場合の例外を処理するメソッド
     * リソースが見つからない場合に呼び出され、エラーメッセージを返します。
     * status: 404 Not Found
     * 
     * <pre>
     * 
     * @param ex
     * @return
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorDto> handleNoResourceFoundException(NoResourceFoundException ex) {
        ErrorDto errors = new ErrorDto();
        errors.setErrorCode(Error.NOT_FOUND_GLOBAL);
        errors.setErrorMessage(message.get("error.global.not_found"));

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    /**
     * <pre>
     * その他の例外を処理するメソッド
     * 予期しない例外が発生した場合に呼び出され、エラーメッセージを返します。
     * status: 500 Internal Server Error
     * </pre>
     * 
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleAllExceptions(Exception ex) {
        ErrorDto errors = new ErrorDto();
        errors.setErrorCode(Error.INTERNAL_SERVER_ERROR_GLOBAL);
        errors.setErrorMessage(message.get("error.global.internal"));
        log.error(ex.getMessage(), ex);

        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
