package sample.dto.response.auth;

import lombok.Getter;
import lombok.Setter;
import sample.dto.ResponseDto;

/**
 * <pre>
 * ログイン情報を格納するDto
 * </pre>
 */
@Getter
@Setter
public class LoginResponseDto extends ResponseDto {
    /** アクセストークン */
    private String accessToken;
}
