package sample.dto.request.employee;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <pre>
 * 社員検索リクエストDTO
 * </pre>
 */
@Data
public class EmployeeSearchRequestDto {
    /** 社員ID */
    private String employeeId;

    /** 社員名 */
    private String name;

    /** 所属部門コード */
    private String departmentCode;

    /** 役職コード */
    private String postCode;

    /** 入社日From */
    private LocalDate enteredAtFrom;

    /** 入社日To */
    private LocalDate enteredAtTo;

    /** ページ番号 */
    @NotNull(message = "ページ番号は必須です。")
    private Integer pageNumber;
}
