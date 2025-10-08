package sample.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;

/**
 * <pre>
 * 社員を定義します。
 * </pre>
 */
@Data
@Entity
@Table(name = "employees")
public class Employee {
    /** 社員ID */
    @Id
    @Size(max = 8)
    private String employeeId;

    /** 社員名 */
    @NotNull
    @Size(max = 256)
    private String name;

    /** 社員名カナ */
    @Size(max = 256)
    @NotNull
    private String nameKana;

    /** 所属部門コード */
    @Size(max = 5)
    @NotNull
    private String departmentCode;

    /** 役職コード */
    @Size(max = 4)
    @NotNull
    private String postCode;

    /** 入社年月日 */
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate enteredAt;

    /** メールアドレス */
    @NotNull
    @Size(max = 256)
    @Email
    @Column(unique = true)
    private String mailAddress;

    /** 電話番号 */
    @NotNull
    @Size(max = 11)
    private String telNumber;

    /** 郵便番号 */
    @NotNull
    @Size(max = 7)
    private String postalCode;

    /** 住所 */
    @NotNull
    @Size(max = 256)
    private String address;

    /** 生年月日 */
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthday;

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
     * 社員状態
     * </pre>
     */
    @Getter
    public enum Status {
        /** 有効 */
        ACTIVE(1, "有効"),
        /** 無効 */
        INACTIVE(0, "無効");

        private final int code;
        private final String label;

        private Status(int code, String label) {
            this.code = code;
            this.label = label;
        }
    }
}
