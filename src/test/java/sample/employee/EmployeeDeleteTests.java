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
import sample.dto.ResultDto;
import sample.dto.response.employee.EmployeeResponseDto;
import sample.model.Employee;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeDeleteTests extends TestHelper {
    @Autowired
    private MockMvc mockMvc;

    private static final String uri = "/api/employee/delete/{employee_id}";

    /**
     * <pre>
     * 社員論理削除後のデータチェック
     * </pre>
     */
    @AfterEach
    private void checkEmployeeDataAfter() throws Exception {
        // 社員情報取得APIを呼び出して、削除した社員情報が存在しないことを確認
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/employee/{employee_id}", "1001");
        MockHttpServletResponse response = this.mockMvc.perform(request)
                .andReturn()
                .getResponse();

        // ステータスの検証
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        // レスポンスの検証
        EmployeeResponseDto actual = objectMapper.readValue(response.getContentAsString(),
                new TypeReference<EmployeeResponseDto>() {
                });

        // ステータスが"0"(無効)であることを確認
        assertEquals(Employee.Status.INACTIVE.getCode(), actual.getStatus());
    }

    /**
     * <pre>
     * [正常系]
     * status: 200 OK
     * 指定した社員IDの社員情報が削除できていること。
     * </pre>
     */
    @Test
    void success() throws Exception {
        // 期待するレスポンス
        ResultDto expected = success_response("success.employee.delete");

        // リクエスト
        String employeeId = "1001";
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(uri, employeeId);
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
}
