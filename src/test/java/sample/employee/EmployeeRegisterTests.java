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
import sample.dto.ResultDto.ResultType;
import sample.dto.request.employee.EmployeeRegisterRequestDto;

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
        request.setEmployeeCode("A1111");
        request.setName("高木 勇気");
        request.setNameKana("タカギ ユウキ");
        request.setMail("yuki_takagi@sample.jp");
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
        // 社員情報取得APIを呼び出して、登録した社員情報が存在することを確認
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/employee/{employee_id}",
                employee.getEmployeeId());
        MockHttpServletResponse response = this.mockMvc.perform(request)
                .andReturn()
                .getResponse();

        // ステータスの検証
        assertEquals(200, response.getStatus());
        // レスポンスの検証
        EmployeeRegisterRequestDto actual = objectMapper.readValue(response.getContentAsString(),
                new TypeReference<EmployeeRegisterRequestDto>() {
                });

        assertEquals(employee.getEmployeeId(), actual.getEmployeeId());
        assertEquals(employee.getEmployeeCode(), actual.getEmployeeCode());
        assertEquals(employee.getName(), actual.getName());
        assertEquals(employee.getNameKana(), actual.getNameKana());
        assertEquals(employee.getMail(), actual.getMail());
        assertEquals(employee.getDepartmentCode(), actual.getDepartmentCode());
        assertEquals(employee.getStatus(), actual.getStatus());
    }

}
