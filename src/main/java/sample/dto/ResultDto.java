package sample.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 結果レスポンスを表すDto
 * </pre>
 */
@Getter
@Setter
public class ResultDto {
    /** 結果 */
    public ResultType result;

    /** メッセージ */
    public String message;

    public enum ResultType {
        SUCCESS("SUCCESS"),
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
