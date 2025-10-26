package sample.service;

import org.hibernate.service.spi.ServiceException;

import sample.dto.request.auth.LoginRequestDto;
import sample.dto.response.auth.LoginResponseDto;

/**
 * <pre>
 * 認証のServiceクラス
 * </pre>
 */
public interface AuthService {

    /**
     * <pre>
     * ログイン認証
     * </pre>
     * 
     * @param requestDto ログインリクエストDto
     * @return ログイン情報
     * @throws ServiceException
     */
    LoginResponseDto login(LoginRequestDto requestDto) throws ServiceException;

}
