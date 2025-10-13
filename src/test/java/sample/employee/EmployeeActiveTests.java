package sample.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import sample.TestHelper;
import sample.dto.ResultDto;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeActiveTests extends TestHelper {
    @Autowired
    private MockMvc mockMvc;

    private static final String uri = "/api/employee/active/{employee_id}";

    /**
     * <pre>
     * 前提条件：論理削除された社員データが存在すること。
     * </pre>
     * 
     * @throws Exception
     */
    @BeforeEach
    public void setup() throws Exception {
        String employeeId = "1001";
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete("/api/employee/delete/{employee_id}", employeeId);
        MockHttpServletResponse result = this.mockMvc.perform(request)
                .andReturn()
                .getResponse();

        assertEquals(200, result.getStatus());
    }

    /**
     * <pre>
     * [正常系]
     * status: 200 OK
     * 論理削除された社員情報が有効化できていること。
     * </pre>
     */
    @Test
    public void success() throws Exception {
        // 期待するレスポンス
        ResultDto expected = success_response("success.employee.active");

        // リクエスト
        String employeeId = "1001";
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .patch(uri, employeeId)
                .contentType("application/json");
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
    }

}
