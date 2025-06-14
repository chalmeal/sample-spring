package sample.service;

import java.util.List;

import sample.context.exception.ServiceException;
import sample.dto.ResultDto;
import sample.dto.request.employee.EmployeeEditRequestDto;
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
    EmployeeResponseDto getEmployee(String employeeId) throws ServiceException;

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

    /**
     * <p>
     * 社員を編集
     * </p>
     * 
     * @param param 更新パラメータ
     * @return 更新結果
     * @throws ServiceException 社員更新に失敗した場合
     */
    ResultDto editEmployee(String employeeId, EmployeeEditRequestDto param) throws ServiceException;

    /**
     * <p>
     * 社員を物理削除
     * </p>
     * 
     * @param employeeId 社員ID
     * @return 削除結果
     * @throws ServiceException 社員削除に失敗した場合
     */
    ResultDto deleteEmployee(String employeeId) throws ServiceException;

}
