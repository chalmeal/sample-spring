package sample.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 結果レスポンスを表すDto
 * </p>
 */
@Getter
@Setter
public class ResultDto {
    /** 結果 */
    public String result;

    /** メッセージ */
    public String message;

    public enum ResultType {
        SUCCESS("OK"),
        FAILURE("NG");

        private String result;

        private ResultType(String result) {
            this.result = result;
        }

        public String getResult() {
            return result;
        }
    }
}
