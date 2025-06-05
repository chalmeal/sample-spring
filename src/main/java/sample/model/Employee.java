package sample.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 社員を定義します。
 * </p>
 */
@Getter
@Setter
@Accessors(chain = true)
public class Employee {
    /** 社員ID */
    private String employeeId;

    /** 社員コード */
    private String employeeCode;

    /** 社員名 */
    private String name;

    /** 社員名カナ */
    private String nameKana;

    /** メールアドレス */
    private String mail;

    /** 所属部門コード */
    private String departmentCode;

    /** 状態 */
    private int status;

    /** 登録日時 */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdAt;

    /** 更新日時 */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime updatedAt;
}
