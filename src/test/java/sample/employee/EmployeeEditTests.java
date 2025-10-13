package sample.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.type.TypeReference;

import sample.TestHelper;
import sample.context.util.Message;
import sample.dto.ResultDto;
import sample.dto.request.employee.EmployeeEditRequestDto;
import sample.dto.response.EmployeeResponseDto;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeEditTests extends TestHelper {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Message message;

    private static final String uri = "/api/employee/edit/{employee_id}";

    /**
     * <pre>
     * 社員情報更新後のデータ存在チェック
     * </pre>
     */
    @AfterEach
    private void checkEmployeeDataExist() throws Exception {
        // 社員情報取得APIを呼び出して、更新した社員情報が存在することを確認
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/employee/{employee_id}",
                "1001");
        EmployeeEditRequestDto employee = success_request();
        MockHttpServletResponse response = this.mockMvc.perform(request)
                .andReturn()
                .getResponse();

        // ステータスの検証
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        // レスポンスの検証
        EmployeeResponseDto actual = objectMapper.readValue(response.getContentAsString(),
                new TypeReference<EmployeeResponseDto>() {
                });

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
    }

    /**
     * <pre>
     * [正常系]
     * status: 200 OK
     * 社員情報が更新できていること。
     * 更新項目：すべて
     * </pre>
     */
    @Test
    void success() throws Exception {
        // 期待するレスポンス
        ResultDto expected = success_response();

        // リクエスト
        String employeeId = "1001";
        EmployeeEditRequestDto body = success_request();
        String requestJson = objectMapper.writeValueAsString(body);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .patch(uri, employeeId)
                .contentType("application/json")
                .content(requestJson);
        MockHttpServletResponse result = this.mockMvc.perform(request)
                .andReturn()
                .getResponse();

        // JSONレスポンスの変換
        ResultDto actual = objectMapper.readValue(result.getContentAsString(), ResultDto.class);

        // ステータスの検証
        assertEquals(HttpStatus.OK.value(), result.getStatus());
        // レスポンスの検証
        assertEquals(expected.getResult(), actual.getResult());
        assertEquals(expected.getMessage(), actual.getMessage());
    }

    /**
     * <pre>
     * successが渡すリクエスト
     * </pre>
     */
    private EmployeeEditRequestDto success_request() {
        EmployeeEditRequestDto request = new EmployeeEditRequestDto();
        request.setName("山田 太郎");
        request.setNameKana("ヤマダ タロウ");
        request.setDepartmentCode("10001");
        request.setPostCode("P001");
        request.setEnteredAt("2020-04-01");
        request.setMailAddress("taro_yamada@sample.jp");
        request.setTelNumber("0312345678");
        request.setPostalCode("1234567");
        request.setAddress("東京都新宿区西新宿2-8-1");
        request.setBirthday("1990-01-01");

        return request;
    }

    /**
     * <pre>
     * successが期待するレスポンス
     * </pre>
     */
    private ResultDto success_response() {
        ResultDto result = new ResultDto();
        result.setResult(ResultDto.ResultType.SUCCESS);
        result.setMessage(message.get("success.employee.edit"));

        return result;
    }
}
