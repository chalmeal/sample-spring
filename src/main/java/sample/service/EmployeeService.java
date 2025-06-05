package sample.service;

import java.util.List;

import sample.context.exception.ServiceException;
import sample.dto.ResultDto;
import sample.dto.request.employee.EmployeeRegisterRequestDto;
import sample.dto.response.EmployeeResponseDto;

/**
 * <pre>
 * 社員のServiceインターフェース
 * </pre>
 */
public interface EmployeeService {

    /**
     * <p>
     * 社員IDから社員を取得
     * 一致する社員が存在しない場合はエラーを返却
     * </p>
     * 
     * @param employeeId 社員ID
     * @return 社員
     */
    EmployeeResponseDto getEmployee(String employeeId);

    /**
     * <p>
     * 社員を検索
     * </p>
     * 
     * @param employeeId     社員ID
     * @param employeeCode   社員コード
     * @param name           名前
     * @param mail           メールアドレス
     * @param departmentCode 所属部門コード
     * @return 社員一覧
     */
    List<EmployeeResponseDto> searchEmployee(String employeeId, String employeeCode, String name, String mail,
            String departmentCode);

    /**
     * <p>
     * 社員を登録
     * </p>
     * 
     * @param param 登録パラメータ
     * @return 登録結果
     * @throws ServiceException 社員登録に失敗した場合
     */
    ResultDto registerEmployee(EmployeeRegisterRequestDto param) throws ServiceException;

}
