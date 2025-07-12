package sample.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

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
import sample.dto.response.EmployeeResponseDto;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeSearchTests extends TestHelper {
    @Autowired
    private MockMvc mockMvc;

    private static final String uri = "/api/employee/search";

    /**
     * <pre>
     * [正常系]
     * status: 200 OK
     * 検索条件に合致する社員情報が取得できていること。
     * 検索条件：なし
     * </pre>
     */
    @Test
    void success_NoParamater() throws Exception {
        // 期待するレスポンス
        EmployeeResponseDto expected = success_NoParamater_response();

        // リクエスト
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(uri + "?employee_id=&employee_code=&name=&mail=&department_code=");
        MockHttpServletResponse result = this.mockMvc.perform(request)
                .andReturn()
                .getResponse();

        // JSONレスポンスの変換
        List<EmployeeResponseDto> actual = objectMapper.readValue(result.getContentAsString(),
                new TypeReference<List<EmployeeResponseDto>>() {
                });

        // ステータスの検証
        assertEquals(HttpStatus.OK.value(), result.getStatus());
        // レスポンスの検証
        assertEquals(5, actual.size());
        assertEquals(expected.getEmployeeId(), actual.get(0).getEmployeeId());
        assertEquals(expected.getEmployeeCode(), actual.get(0).getEmployeeCode());
        assertEquals(expected.getName(), actual.get(0).getName());
        assertEquals(expected.getNameKana(), actual.get(0).getNameKana());
        assertEquals(expected.getMail(), actual.get(0).getMail());
        assertEquals(expected.getDepartmentCode(), actual.get(0).getDepartmentCode());
    }

    /**
     * <pre>
     * success_NoParamaterが期待するレスポンス
     * </pre>
     */
    private EmployeeResponseDto success_NoParamater_response() {
        // 項目の検証
        // 項目の検証は1レコード目のみ実施
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
     * [正常系]
     * status: 200 OK
     * 検索条件に合致する社員情報が取得できていること
     * 検索条件：社員ID
     * </pre>
     */
    @Test
    void success_EmployeeId() throws Exception {
    }

    /**
     * <pre>
     * success_EmployeeIdが期待するレスポンス
     * </pre>
     */
    private EmployeeResponseDto success_EmployeeId_response() {
        // 項目の検証
        // 項目の検証は1レコード目のみ実施
        EmployeeResponseDto response = new EmployeeResponseDto();

        return response;
    }

    /**
     * <pre>
     * [正常系]
     * status: 200 OK
     * 検索条件に合致する社員情報が取得できていること
     * 検索条件：社員コード
     * </pre>
     */
    @Test
    void success_EmployeeCode() throws Exception {
    }

    /**
     * <pre>
     * success_EmployeeCodeが期待するレスポンス
     * </pre>
     */
    private EmployeeResponseDto success_EmployeeCode_response() {
        // 項目の検証
        // 項目の検証は1レコード目のみ実施
        EmployeeResponseDto response = new EmployeeResponseDto();

        return response;
    }

    /**
     * <pre>
     * [正常系]
     * status: 200 OK
     * 検索条件に合致する社員情報が取得できていること
     * 検索条件：社員名
     * </pre>
     */
    @Test
    void success_Name() throws Exception {
    }

    /**
     * <pre>
     * success_Nameが期待するレスポンス
     * </pre>
     */
    private EmployeeResponseDto success_Name_response() {
        // 項目の検証
        // 項目の検証は1レコード目のみ実施
        EmployeeResponseDto response = new EmployeeResponseDto();

        return response;
    }

    /**
     * <pre>
     * [正常系]
     * status: 200 OK
     * 検索条件に合致する社員情報が取得できていること
     * 検索条件：社員名カナ
     * </pre>
     */
    @Test
    void success_NameKana() throws Exception {
    }

    /**
     * <pre>
     * success_NameKanaが期待するレスポンス
     * </pre>
     */
    private EmployeeResponseDto success_NameKana_response() {
        // 項目の検証
        // 項目の検証は1レコード目のみ実施
        EmployeeResponseDto response = new EmployeeResponseDto();

        return response;
    }

    /**
     * <pre>
     * [正常系]
     * status: 200 OK
     * 検索条件に合致する社員情報が取得できていること
     * 検索条件：メールアドレス
     * </pre>
     */
    @Test
    void success_Mail() throws Exception {
    }

    /**
     * <pre>
     * success_Mailが期待するレスポンス
     * </pre>
     */
    private EmployeeResponseDto success_Mail_response() {
        // 項目の検証
        // 項目の検証は1レコード目のみ実施
        EmployeeResponseDto response = new EmployeeResponseDto();

        return response;
    }

    /**
     * <pre>
     * [正常系]
     * status: 200 OK
     * 検索条件に合致する社員情報が取得できていること
     * 検索条件：所属部門コード
     * </pre>
     */
    @Test
    void success_DepartmentCode() throws Exception {
    }

    /**
     * <pre>
     * success_DepartmentCodeが期待するレスポンス
     * </pre>
     */
    private EmployeeResponseDto success_DepartmentCode_response() {
        // 項目の検証
        // 項目の検証は1レコード目のみ実施
        EmployeeResponseDto response = new EmployeeResponseDto();

        return response;
    }

    /**
     * <pre>
     * [正常系]
     * status: 200 OK
     * 複数の検索条件に合致する社員情報が取得できていること
     * 検索条件：社員名、所属部門コード
     * </pre>
     */
    @Test
    void success_MultipleConditions() throws Exception {
    }

    /**
     * <pre>
     * success_MultipleConditionsが期待するレスポンス
     * </pre>
     */
    private EmployeeResponseDto success_MultipleConditions_response() {
        // 項目の検証
        // 項目の検証は1レコード目のみ実施
        EmployeeResponseDto response = new EmployeeResponseDto();

        return response;
    }

}
