package sample.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import sample.constant.ResponseConst;
import sample.dto.ErrorDto;
import sample.dto.ResponseDto;

/**
 * <pre>
 * Controller基底クラス
 * </pre>
 */
public class SampleController {

    /**
     * <pre>
     * Success(200)
     * ResponseEntityにServiceからの結果値を格納してクライアントに返却
     * </pre>
     * 
     * @param resDto
     * @return ResponseEntity<ResponseDto>
     */
    protected ResponseEntity<ResponseDto> response(ResponseDto resDto) {
        return new ResponseEntity<ResponseDto>(resDto, ResponseConst.Status.SUCCESS.getStatus());
    }

    /**
     * <pre>
     * Error
     * ResponseEntityにServiceからの結果値を格納してクライアントに返却
     * </pre>
     * 
     * @param statusCode
     * @param errorCode
     * @param errorMessage
     * @return ResponseEntity<ErrorDto>
     */
    protected ResponseEntity<ErrorDto> response(HttpStatus statusCode, String errorCode, String errorMessage) {
        ErrorDto error = new ErrorDto();
        error.setErrorCode(errorCode);
        error.setErrorMessage(errorMessage);

        return new ResponseEntity<ErrorDto>(error, statusCode);
    }
}
