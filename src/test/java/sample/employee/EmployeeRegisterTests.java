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
import sample.context.util.Message;
import sample.dto.ResultDto;
import sample.dto.ResultDto.ResultType;
import sample.dto.request.employee.EmployeeRegisterRequestDto;
import sample.dto.response.EmployeeResponseDto;
import sample.model.Employee;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeRegisterTests extends TestHelper {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Message message;

    private static final String uri = "/api/employee/register";

    /**
     * <pre>
     * [正常系]
     * status: 201 Created
     * 社員情報が登録できていること。
     * 登録項目：すべて
     * </pre>
     */
    @Test
    void success() throws Exception {
        // 期待するレスポンス
        ResultDto expected = success_response();

        // リクエスト
        EmployeeRegisterRequestDto body = success_request();
        String requestJson = objectMapper.writeValueAsString(body);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(uri)
                .contentType("application/json")
                .content(requestJson);
        MockHttpServletResponse result = this.mockMvc.perform(request)
                .andReturn()
                .getResponse();

        // JSONレスポンスの変換
        ResultDto actual = objectMapper.readValue(result.getContentAsString(), ResultDto.class);

        // ステータスの検証
        assertEquals(201, result.getStatus());
        // レスポンスの検証
        assertEquals(expected.getResult(), actual.result);
        assertEquals(expected.getMessage(), actual.message);

        // 社員情報登録後のデータ存在チェック
        checkEmployeeDataExist(body);
    }

    /**
     * <pre>
     * successが渡すリクエスト
     * </pre>
     */
    private EmployeeRegisterRequestDto success_request() {
        EmployeeRegisterRequestDto request = new EmployeeRegisterRequestDto();
        request.setEmployeeId("1111");
        request.setName("テスト 太郎");
        request.setNameKana("テスト タロウ");
        request.setDepartmentCode("10001");
        request.setPostCode("P001");
        request.setEnteredAt("2023-01-01");
        request.setMailAddress("taro_test@sample.jp");
        request.setTelNumber("0312345678");
        request.setPostalCode("1000001");
        request.setAddress("東京都千代田区千代田1-1");
        request.setBirthday("1990-01-01");

        return request;
    }

    /**
     * <pre>
     * successが期待するレスポンス
     * </pre>
     */
    private ResultDto success_response() {
        ResultDto response = new ResultDto();
        response.setResult(ResultType.SUCCESS);
        response.setMessage(message.get("success.employee.register"));

        return response;
    }

    /**
     * <pre>
     * 社員情報登録後のデータ存在チェック
     * </pre>
     */
    private void checkEmployeeDataExist(EmployeeRegisterRequestDto employee) throws Exception {
        String uri = "/api/employee";

        // リクエスト
        String employeeId = "1111";
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(uri + "/{employee_id}", employeeId);
        MockHttpServletResponse result = this.mockMvc.perform(request)
                .andReturn()
                .getResponse();

        // JSONレスポンスの変換
        EmployeeResponseDto actual = objectMapper.readValue(result.getContentAsString(), EmployeeResponseDto.class);

        // ステータスの検証
        assertEquals(HttpStatus.OK.value(), result.getStatus());
        // レスポンスの検証
        assertEquals(employee.getEmployeeId(), actual.getEmployeeId());
        assertEquals(employee.getName(), actual.getName());
        assertEquals(employee.getNameKana(), actual.getNameKana());
        assertEquals(employee.getDepartmentCode(), actual.getDepartmentCode());
        assertEquals(employee.getPostCode(), actual.getPostCode());
        assertEquals(employee.getEnteredAt(), actual.getEnteredAt().toString());
        assertEquals(employee.getMailAddress(), actual.getMailAddress());
        assertEquals(employee.getTelNumber(), actual.getTelNumber());
        assertEquals(employee.getPostalCode(), actual.getPostalCode());
        assertEquals(employee.getAddress(), actual.getAddress());
        assertEquals(employee.getBirthday(), actual.getBirthday().toString());
        assertEquals(1, Employee.Status.ACTIVE.getCode());
    }

}
