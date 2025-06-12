package sample.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.RequiredArgsConstructor;
import sample.context.constant.error.EmployeeError;
import sample.context.util.Message;
import sample.dto.ErrorDto;

/**
 * <p>
 * REST APIのエラー処理を行うクラス
 * </p>
 */
@ControllerAdvice
@RequiredArgsConstructor
public class RestErrorAdvice {
    // DI
    private final Message message;

    /**
     * <p>
     * バリデーションエラーを処理するメソッド
     * バリデーションエラーが発生した場合に呼び出され、エラーメッセージをMap形式で返します。
     * </p>
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
     * <p>
     * その他の例外を処理するメソッド
     * 予期しない例外が発生した場合に呼び出され、エラーメッセージを返します。
     * </p>
     * 
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleAllExceptions(Exception ex) {
        ErrorDto errors = new ErrorDto();
        errors.setErrorCode(EmployeeError.INTERNAL_SERVER_ERROR);
        errors.setErrorMessage(message.getMessage("error.internal.server"));

        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
