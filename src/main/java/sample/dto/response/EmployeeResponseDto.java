package sample.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import sample.dto.ResponseDto;

/**
 * <pre>
 * 社員情報を保持するDto
 * </pre>
 */
@Getter
@Setter
public class EmployeeResponseDto extends ResponseDto {
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

    /** 入社年月 */
    private LocalDate enteredAt;

    /** メールアドレス */
    private String mailAddress;

    /** 電話番号 */
    private String telNumber;

    /** 郵便番号 */
    private String postalCode;

    /** 住所 */
    private String address;

    /** 生年月日 */
    private LocalDate birthday;

    /** 登録日時 */
    private LocalDateTime createdAt;

    /** 更新日時 */
    private LocalDateTime updatedAt;

    /** 状態 */
    private int status;
}
