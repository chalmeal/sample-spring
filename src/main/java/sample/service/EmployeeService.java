package sample.service;

import java.time.LocalDate;

import sample.context.Pagination;
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
     * <pre>
     * 社員IDから社員を取得
     * 一致する社員が存在しない場合はエラーを返却
     * </pre>
     * 
     * @param employeeId 社員ID
     * @return 社員
     */
    EmployeeResponseDto getEmployee(String employeeId) throws ServiceException;

    /**
     * <pre>
     * 社員を検索
     * </pre>
     * 
     * @param employeeId     社員ID
     * @param name           名前
     * @param departmentCode 所属部門コード
     * @param postCode       役職コード
     * @param mail           メールアドレス
     * @param status         状態
     * @return 社員一覧
     */
    Pagination<EmployeeResponseDto> searchEmployee(String employeeId, String name, String departmentCode,
            String postCode,
            LocalDate enteredAtFrom, LocalDate enteredAtTo, String status);

    /**
     * <pre>
     * 社員を登録
     * </pre>
     * 
     * @param param 登録パラメータ
     * @return 登録結果
     * @throws ServiceException 社員登録に失敗した場合
     */
    ResultDto registerEmployee(EmployeeRegisterRequestDto param) throws ServiceException;

    /**
     * <pre>
     * 社員を編集
     * </pre>
     * 
     * @param param 更新パラメータ
     * @return 更新結果
     * @throws ServiceException 社員更新に失敗した場合
     */
    ResultDto editEmployee(String employeeId, EmployeeEditRequestDto param) throws ServiceException;

    /**
     * <pre>
     * 社員を物理削除
     * </pre>
     * 
     * @param employeeId 社員ID
     * @return 削除結果
     * @throws ServiceException 社員削除に失敗した場合
     */
    ResultDto deleteEmployee(String employeeId) throws ServiceException;

}
