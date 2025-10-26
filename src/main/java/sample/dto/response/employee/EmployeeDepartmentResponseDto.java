package sample.dto.response.employee;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sample.dto.ResponseDto;

/**
 * <pre>
 * 社員所属部門情報を保持するDto
 * </pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class EmployeeDepartmentResponseDto extends ResponseDto {
    /** 社員ID */
    private String employeeId;

    /** 所属部門コード */
    private String departmentCode;

    /** 所属部門名 */
    private String departmentName;

    /** 所属部門長社員ID */
    private String managerEmployeeId;
}
