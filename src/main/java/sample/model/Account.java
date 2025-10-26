package sample.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;

/**
 * <pre>
 * アカウントを定義します。
 * </pre>
 */
@Data
@Entity
@Table(name = "accounts")
public class Account {
    /** アカウントID */
    @Id
    @Size(max = 64)
    private String accountId;

    /** アカウント名 */
    @NotNull
    @Size(max = 256)
    private String accountName;

    /** メールアドレス */
    @NotNull
    @Size(max = 256)
    @Column(unique = true)
    private String mailAddress;

    /** パスワード */
    @Size(max = 512)
    private String password;

    /** 権限 */
    @NotNull
    private int authority;

    /** 登録日時 */
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdAt;

    /** 更新日時 */
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime updatedAt;

    /** 状態 */
    @NotNull
    private int status;

    /**
     * <pre>
     * アカウント権限
     * </pre>
     */
    @Getter
    public enum Authority {
        /** 一般 */
        NORMAL(1, "一般"),
        /** 管理者 */
        ADMIN(2, "管理者");

        private final int code;
        private final String label;

        private Authority(int code, String label) {
            this.code = code;
            this.label = label;
        }
    }

    /**
     * <pre>
     * アカウント状態
     * </pre>
     */
    @Getter
    public enum Status {
        /** 無効 */
        INACTIVE(0, "無効"),
        /** 有効 */
        ACTIVE(1, "有効");

        private final int code;
        private final String label;

        private Status(int code, String label) {
            this.code = code;
            this.label = label;
        }
    }
}
