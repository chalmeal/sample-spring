package sample.dto.request.auth;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequestDto {
    /** アカウントID */
    @NotNull
    private String accountId;

    /** パスワード */
    @NotNull
    private String password;
}
