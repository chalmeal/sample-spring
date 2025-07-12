package sample.context.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

/**
 * <pre>
 * Serviceエラークラス
 * Service層で発生する例外を表します。
 * </pre>
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
     * <pre>
     * ServiceExceptionコンストラクタ
     * ステータスコード、エラーコード、エラーメッセージを指定してServiceExceptionを生成します。
     * Controllerにエラーを返す際に使用します。
     * </pre>
     * 
     * @param statusCode ステータスコード
     * @param errorCode  エラーコード
     * @param msg        エラーメッセージ
     */
    public ServiceException(HttpStatus statusCode, String errorCode, String msg) {
        super();
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.errorMessage = msg;
    }
}
