package sample.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * <pre>
 * 部署を定義します。
 * </pre>
 */
@Data
@Entity
@Table(name = "departments")
public class Department {
    /** 部署コード */
    @Id
    @Size(max = 5)
    private String departmentCode;

    /** 部署名 */
    @NotNull
    @Size(max = 256)
    private String departmentName;

    /** 部門長社員ID */
    @NotNull
    @Size(max = 256)
    private String managerEmployeeId;

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
}