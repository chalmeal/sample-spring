package sample.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * エラー情報を保持するDto
 * </p>
 */
@Getter
@Setter
public class ErrorDto {
    /** エラーコード */
    public String errorCode;

    /** エラーメッセージ */
    public String errorMessage;
}
