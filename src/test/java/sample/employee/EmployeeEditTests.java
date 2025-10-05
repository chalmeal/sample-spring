package sample.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.type.TypeReference;

import sample.TestHelper;
import sample.context.util.Message;
import sample.dto.ResultDto;
import sample.dto.request.employee.EmployeeEditRequestDto;

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
        assertEquals(200, result.getStatus());
        // レスポンスの検証
        assertEquals(expected.getResult(), actual.getResult());
        assertEquals(expected.getMessage(), actual.getMessage());

        checkEmployeeDataExist(employeeId, body);
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
        request.setMail("taro_yamada@sample.jp");
        request.setDepartmentCode("100001");
        request.setStatus("1");

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

    /**
     * <pre>
     * 社員情報更新後のデータ存在チェック
     * </pre>
     */
    private void checkEmployeeDataExist(String employeeId, EmployeeEditRequestDto employee) throws Exception {
        // 社員情報取得APIを呼び出して、更新した社員情報が存在することを確認
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/employee/{employee_id}",
                employeeId);
        MockHttpServletResponse response = this.mockMvc.perform(request)
                .andReturn()
                .getResponse();

        // ステータスの検証
        assertEquals(200, response.getStatus());
        // レスポンスの検証
        EmployeeEditRequestDto actual = objectMapper.readValue(response.getContentAsString(),
                new TypeReference<EmployeeEditRequestDto>() {
                });

        assertEquals(employee.getName(), actual.getName());
        assertEquals(employee.getNameKana(), actual.getNameKana());
        assertEquals(employee.getMail(), actual.getMail());
        assertEquals(employee.getDepartmentCode(), actual.getDepartmentCode());
        assertEquals(employee.getStatus(), actual.getStatus());
    }
}
