package sample.context.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import sample.context.constant.ResponseConst.Error;

/**
 * Serviceエラークラス
 */
@Getter
public class ServiceException extends RuntimeException {

    // ステータスコード
    private HttpStatus statusCode;

    // エラーコード
    private String errorCode;

    // エラーメッセージ
    private String errorMessage;

    /**
     * @param HttpStatus ステータスコード
     * @param String     エラーコード
     * @param String     エラーメッセージ
     */
    public ServiceException(HttpStatus statusCode, Error error) {
        super();
        this.statusCode = statusCode;
        this.errorCode = error.getErrorCode();
        this.errorMessage = error.getErrorMessage();
    }
}
