package sample.service.impl;

import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sample.context.util.Message;
import sample.dto.request.auth.LoginRequestDto;
import sample.dto.response.auth.LoginResponseDto;
import sample.service.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    // DI
    // Repository

    // Message
    private final Message message;

    /**
     * {@inheritDoc}
     */
    @Override
    public LoginResponseDto login(LoginRequestDto param) throws ServiceException {
        // TODO: Implement login logic
        return null;
    }
}
