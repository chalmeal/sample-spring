package sample.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import sample.TestHelper;
import sample.context.constant.error.EmployeeError;
import sample.context.util.Message;
import sample.dto.ErrorDto;
import sample.dto.response.EmployeeResponseDto;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeGetTests extends TestHelper {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Message message;

    private static final String uri = "/api/employee";

    /**
     * <pre>
     * [正常系]
     * status: 200 OK
     * 指定した社員IDの社員情報が取得できていること。
     * </pre>
     */
    @Test
    void success() throws Exception {
        // 期待するレスポンス
        EmployeeResponseDto expected = success_response();

        // リクエスト
        String employeeId = "1001";
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(uri + "/{employee_id}", employeeId);
        MockHttpServletResponse result = this.mockMvc.perform(request)
                .andReturn()
                .getResponse();

        // JSONレスポンスの変換
        EmployeeResponseDto actual = objectMapper.readValue(result.getContentAsString(), EmployeeResponseDto.class);

        // ステータスの検証
        assertEquals(HttpStatus.OK.value(), result.getStatus());
        // レスポンスの検証
        assertEquals(expected.getEmployeeId(), actual.getEmployeeId());
        assertEquals(expected.getEmployeeCode(), actual.getEmployeeCode());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getNameKana(), actual.getNameKana());
        assertEquals(expected.getMail(), actual.getMail());
        assertEquals(expected.getDepartmentCode(), actual.getDepartmentCode());
    }

    /**
     * <pre>
     * success_01が期待するレスポンス
     * </pre>
     */
    private EmployeeResponseDto success_response() {
        EmployeeResponseDto response = new EmployeeResponseDto();
        response.setEmployeeId("1001");
        response.setEmployeeCode("A0001");
        response.setName("山田 太郎");
        response.setNameKana("ヤマダ タロウ");
        response.setMail("taro_yamada@sample.jp");
        response.setDepartmentCode("100001");
        response.setStatus(1);

        return response;
    }

    /**
     * <pre>
     * [異常系]
     * status: 400 Bad Request
     * 存在しない社員IDを指定した場合、社員情報が取得できないこと。
     * </pre>
     */
    @Test
    void failure_NotExistEmployee() throws Exception {
        // 期待するレスポンス
        ErrorDto expected = failure_NotExistEmployee_response();

        // リクエスト
        String employeeId = "9999"; // 存在しない社員ID
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(uri + "/{employee_id}", employeeId);
        MockHttpServletResponse result = this.mockMvc.perform(request)
                .andReturn()
                .getResponse();

        // JSONレスポンスの変換
        ErrorDto actual = objectMapper.readValue(result.getContentAsString(), ErrorDto.class);

        // ステータスの検証
        assertEquals(HttpStatus.NOT_FOUND.value(), result.getStatus());
        // レスポンスの検証
        assertEquals(expected.errorCode, actual.getErrorCode());
        assertEquals(expected.errorMessage, actual.getErrorMessage());
    }

    /**
     * <pre>
     * failure_NotExistEmployee_01が期待するレスポンス
     * </pre>
     */
    private ErrorDto failure_NotExistEmployee_response() {
        ErrorDto response = new ErrorDto();
        response.setErrorCode(EmployeeError.getError(EmployeeError.NOT_EXISTS));
        response.setErrorMessage(message.getMessage("error.employee.notfound"));

        return response;
    }

    /**
     * <pre>
     * [異常系]
     * status: 404 Not Found
     * 社員IDを指定せずに社員情報を取得しようとした場合、エラーが返されること。
     * </pre>
     */
    @Test
    void failure_NotFoundResource() throws Exception {
        // 期待するレスポンス
        ErrorDto expected = failure_NotFoundResource_response();

        // リクエスト
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/employee");
        MockHttpServletResponse result = this.mockMvc.perform(request)
                .andReturn()
                .getResponse();

        // JSONレスポンスの変換
        ErrorDto actual = objectMapper.readValue(result.getContentAsString(), ErrorDto.class);

        // ステータスの検証
        assertEquals(HttpStatus.NOT_FOUND.value(), result.getStatus());
        // レスポンスの検証
        assertEquals(expected.getErrorCode(), actual.getErrorCode());
        assertEquals(expected.getErrorMessage(), actual.getErrorMessage());
    }

    /**
     * <pre>
     * failure_NotFoundResource_01が期待するレスポンス
     * </pre>
     */
    private ErrorDto failure_NotFoundResource_response() {
        ErrorDto response = new ErrorDto();
        response.setErrorCode(EmployeeError.NOT_FOUND);
        response.setErrorMessage(message.getMessage("error.global.not_found"));

        return response;
    }

}
