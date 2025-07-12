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
     * <pre>
     * OK(200)
     * ResponseEntityにServiceからの結果値を格納してクライアントに返却
     * </pre>
     * 
     * @param resDto
     * @return ResponseEntity<ResponseDto>
     */
    protected ResponseEntity<ResponseDto> responseOK(ResponseDto resDto) {
        return new ResponseEntity<ResponseDto>(resDto, HttpStatus.OK);
    }

    /**
     * <pre>
     * OK(200)
     * ResponseEntityにServiceからの結果値を格納してクライアントに返却
     * 
     * @param resDto
     * @return ResponseEntity<ResultDto>
     */
    protected ResponseEntity<ResultDto> responseOK(ResultDto resDto) {
        return new ResponseEntity<ResultDto>(resDto, HttpStatus.OK);
    }

    /**
     * <pre>
     * Success(201)
     * ResponseEntityにServiceからの結果値を格納してクライアントに返却
     * </pre>
     * 
     * @param result
     * @param message
     * @return ResponseEntity<ResultDto>
     */
    protected ResponseEntity<ResultDto> responseCreate(ResultDto result) {
        return new ResponseEntity<ResultDto>(result, HttpStatus.CREATED);
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
