package sample.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * エラーDto
 */
@Getter
@Setter
public class ErrorDto {
    /** エラーコード */
    public String errorCode;

    /** エラーメッセージ */
    public String errorMessage;
}
