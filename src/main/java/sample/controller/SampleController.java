package sample.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import sample.dto.ErrorDto;
import sample.dto.ResponseDto;
import sample.dto.ResultDto;

/**
 * <pre>
 * Controller基底クラス
 * </pre>
 */
public class SampleController {

    /**
     * <p>
     * Success(200)
     * ResponseEntityにServiceからの結果値を格納してクライアントに返却
     * </p>
     * 
     * @param resDto
     * @return ResponseEntity<ResponseDto>
     */
    protected ResponseEntity<ResponseDto> response(ResponseDto resDto) {
        return new ResponseEntity<ResponseDto>(resDto, HttpStatus.OK);
    }

    /**
     * <p>
     * Success(201)
     * ResponseEntityにServiceからの結果値を格納してクライアントに返却
     * </p>
     * 
     * @param result
     * @param message
     * @return ResponseEntity<ResultDto>
     */
    protected ResponseEntity<ResultDto> response(ResultDto result) {
        return new ResponseEntity<ResultDto>(result, HttpStatus.CREATED);
    }

    /**
     * <p>
     * Error
     * ResponseEntityにServiceからの結果値を格納してクライアントに返却
     * </p>
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
