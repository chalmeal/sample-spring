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
import lombok.Getter;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    /** 実行者ID */
    @NotNull
    @Size(max = 64)
    private String executorId;

    /** 処理名 */
    @NotNull
    @Size(max = 128)
    private String processName;

    /** ログ種別 */
    @NotNull
    private int logType;

    /** カテゴリ */
    @NotNull
    private int category;

    /** メッセージ */
    @Size(max = 2000)
    private String message;

    /** 実行日時 */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime executedAt;

    /**
     * <pre>
     * ログ種別
     * </pre>
     */
    @Getter
    public enum LogType {
        /** 成功 */
        SUCCESS(1, "成功"),
        /** 失敗 */
        ERROR(2, "失敗"),
        /** 警告 */
        WARN(3, "警告"),
        /** 実行中 */
        PROCESSING(4, "実行中");

        private final int code;
        private final String label;

        private LogType(int code, String label) {
            this.code = code;
            this.label = label;
        }
    }

    /**
     * <pre>
     * カテゴリ
     * </pre>
     */
    @Getter
    public enum Category {
        /** API */
        API(1, "API"),
        /** バッチ */
        BATCH(2, "バッチ");

        private final int code;
        private final String label;

        private Category(int code, String label) {
            this.code = code;
            this.label = label;
        }
    }
}
