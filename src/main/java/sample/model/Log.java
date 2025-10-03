package sample.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * <pre>
 * ログを定義します。
 * </pre>
 */
@Data
@Entity
@Table(name = "logs")
public class Log {
    /** ログID */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long logId;

    /** 実行者ID */
    @NotNull
    @Size(max = 64)
    private String executorId;

    /** ログ種別 */
    @NotNull
    @Size(max = 20)
    private String logType;

    /** カテゴリ */
    @NotNull
    @Size(max = 20)
    private String category;

    /** メッセージ */
    @Size(max = 2000)
    private String message;

    /** エラー事由 */
    @Size(max = 2000)
    private String errorReason;

    /** 実行日時 */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime executedAt;
}
