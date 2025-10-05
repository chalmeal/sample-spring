package sample.dto.request.employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * <pre>
 * 社員登録リクエストDTO
 * </pre>
 */
@Data
public class EmployeeRegisterRequestDto {
    /** 社員ID */
    @NotNull(message = "{error.employee.id.required}")
    @Size(max = 8, message = "{error.employee.id.max}")
    private String employeeId;

    /** 名前 */
    @NotNull(message = "{error.employee.name.required}")
    @Size(max = 256, message = "{error.employee.name.max}")
    private String name;

    /** 名前カナ */
    @NotNull(message = "{error.employee.nameKana.required}")
    @Size(max = 256, message = "{error.employee.nameKana.max}")
    @Pattern(regexp = "^[ァ-ヶー\\s]+$", message = "{error.employee.nameKana.kana}")
    private String nameKana;

    /** 部署コード */
    @NotNull(message = "{error.employee.departmentCode.required}")
    @Size(max = 5, message = "{error.employee.departmentCode.max}")
    private String departmentCode;

    /** 役職コード */
    @NotNull(message = "{error.employee.postCode.required}")
    @Size(max = 4, message = "{error.employee.postCode.max}")
    private String postCode;

    /** 入社年月日 */
    @NotNull(message = "{error.employee.enteredAt.required}")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "{error.employee.enteredAt.format}")
    private String enteredAt;

    /** メールアドレス */
    @NotNull(message = "{error.employee.mail.required}")
    @Size(max = 100, message = "{error.employee.mail.max}")
    @Email(message = "{error.employee.mail.format}")
    private String mailAddress;

    /** 電話番号 */
    @NotNull(message = "{error.employee.telNumber.required}")
    @Size(max = 11, message = "{error.employee.telNumber.max}")
    private String telNumber;

    /** 郵便番号 */
    @NotNull(message = "{error.employee.postalCode.required}")
    @Size(max = 7, message = "{error.employee.postalCode.max}")
    private String postalCode;

    /** 住所 */
    @NotNull(message = "{error.employee.address.required}")
    @Size(max = 256, message = "{error.employee.address.max}")
    private String address;

    /** 生年月日 */
    @NotNull(message = "{error.employee.birthday.required}")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "{error.employee.birthday.format}")
    private String birthday;

}