package sample.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sample.context.exception.ServiceException;
import sample.dto.request.auth.LoginRequestDto;
import sample.service.AuthService;

/**
 * <pre>
 * 認証のControllerクラス
 * </pre>
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController extends SampleController {
    // DI
    private final AuthService service;

    /**
     * <pre>
     * ログインAPI
     * </pre>
     * 
     * @param param
     * @return ログイン情報
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto param) {
        try {
            return responseOK(service.login(param));
        } catch (ServiceException e) {
            return response(e.getStatusCode(), e.getErrorCode(), e.getErrorMessage());
        }
    }

}
