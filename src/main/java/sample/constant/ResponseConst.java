package sample.constant;

import org.springframework.http.HttpStatus;

import sample.dto.ErrorDto;

/**
 * アプリケーションレスポンス定義
 */
public class ResponseConst {
    /**
     * <pre>
     * HTTPステータス定義
     * </pre>
     */
    public enum Status {
        // 200
        SUCCESS(HttpStatus.OK),
        // 400
        BAD_REQUEST(HttpStatus.BAD_REQUEST),
        // 401
        UNAUTHORIZED(HttpStatus.UNAUTHORIZED),
        // 403
        FORBIDDEN(HttpStatus.FORBIDDEN),
        // 404
        NOT_FOUND(HttpStatus.NOT_FOUND);

        private HttpStatus code;

        private Status(HttpStatus code) {
            this.code = code;
        }

        public HttpStatus getStatus() {
            return code;
        }
    }

    /**
     * <pre>
     * エラー定義
     * </pre>
     */
    public enum Error {
        // EMPLOYEE
        EMPLOYEE_GET_NOT_FOUND("E400-1001", "対象の社員が見つかりませんでした。");

        // エラーコード
        private final String code;
        // エラーメッセージ
        private final String message;

        private Error(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getErrorCode() {
            return code;
        }

        public String getErrorMessage() {
            return message;
        }

        public ErrorDto setErrorDto() {
            ErrorDto error = new ErrorDto();
            error.setErrorCode(code);
            error.setErrorMessage(message);
            return error;
        }
    }

}
