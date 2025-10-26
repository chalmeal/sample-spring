package sample.dto.response.employee;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sample.dto.ResponseDto;

/**
 * <pre>
 * 社員検索レスポンスDto
 * </pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class EmployeeSearchResponseDto extends ResponseDto {
    /** 社員ID */
    private String employeeId;

    /** 社員名 */
    private String name;

    /** 社員名カナ */
    private String nameKana;

    /** 所属部門コード */
    private String departmentCode;

    /** 役職コード */
    private String postCode;

}
