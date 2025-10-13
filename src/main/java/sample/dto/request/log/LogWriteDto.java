package sample.dto.request.log;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * <pre>
 * ログ書き込み用Dto
 * </pre>
 */
@Data
public class LogWriteDto {

    /** ログID */
    private Long logId;

    /** 実行者ID */
    private String executorId;

    /** 処理名 */
    private String processName;

    /** ログ種別 */
    private int logType;

    /** カテゴリ */
    private int category;

    /** メッセージ */
    private String message;

    /** 実行日時 */
    private LocalDateTime executedAt;
}
