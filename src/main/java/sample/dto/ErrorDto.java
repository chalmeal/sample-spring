package sample.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * エラー情報を保持するDto
 * </pre>
 */
@Getter
@Setter
public class ErrorDto {
    /** エラーコード */
    public String errorCode;

    /** エラーメッセージ */
    public String errorMessage;
}
