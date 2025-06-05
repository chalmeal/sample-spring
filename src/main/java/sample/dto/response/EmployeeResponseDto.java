package sample.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import sample.dto.ResponseDto;

/**
 * <p>
 * 社員情報を保持するDto
 * </p>
 */
@Getter
@Setter
@Accessors(chain = true)
public class EmployeeResponseDto extends ResponseDto {
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
}
