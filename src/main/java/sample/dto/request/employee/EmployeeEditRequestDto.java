package sample.dto.request.employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <pre>
 * 社員編集リクエストDTO
 * </pre>
 */
@Getter
@Setter
@Accessors(chain = true)
public class EmployeeEditRequestDto {
    /** 名前 */
    @NotNull(message = "{error.employee.name.required}")
    @Size(max = 50, message = "{error.employee.name.max}")
    private String name;

    /** 名前カナ */
    @NotNull(message = "{error.employee.nameKana.required}")
    @Size(max = 50, message = "{error.employee.nameKana.max}")
    @Pattern(regexp = "^[ァ-ヶー\\s]+$", message = "{error.employee.nameKana.kana}")
    private String nameKana;

    /** 部署コード */
    @NotNull(message = "{error.employee.departmentCode.required}")
    @Size(max = 8, message = "{error.employee.departmentCode.max}")
    private String departmentCode;

    /** メールアドレス */
    @NotNull(message = "{error.employee.mail.required}")
    @Size(max = 100, message = "{error.employee.mail.max}")
    @Email(message = "{error.employee.mail.format}")
    private String mail;

    /** ステータス */
    @NotNull(message = "{error.employee.status.required}")
    @Pattern(regexp = "^(0|1)$", message = "{error.employee.status.invalid}")
    private String status;
}
