package sample.repository;

import java.time.LocalDate;
import java.util.List;

import sample.dto.request.employee.EmployeeEditRequestDto;
import sample.dto.request.employee.EmployeeRegisterRequestDto;
import sample.model.Employee.EmployeeDepartmentEntity;
import sample.model.Employee.EmployeeEntity;
import sample.model.Employee.EmployeeSearchEntity;

/**
 * <pre>
 * 社員のRepositoryインターフェース
 * </pre>
 */
public interface EmployeeRepository {

    /**
     * <pre>
     * 社員IDから社員を取得
     * </pre>
     * 
     * @param employeeId 社員ID
     * @return 社員
     */
    EmployeeEntity getEmployeeById(String employeeId);

    /**
     * <pre>
     * 社員を検索
     * </pre>
     * 
     * @param employeeId     社員ID
     * @param employeeCode   社員コード
     * @param name           名前
     * @param mail           メールアドレス
     * @param departmentCode 所属部門コード
     * @param postCode       役職コード
     * @param enteredAtFrom  入社日From
     * @param enteredAtTo    入社日To
     * @param status         状態
     * @param pageNumber     ページ番号
     * @return 社員
     */
    List<EmployeeSearchEntity> searchEmployee(String employeeId, String name,
            String departmentCode, String postCode, LocalDate enteredAtFrom, LocalDate enteredAtTo,
            Integer pageNumber);

    /**
     * <pre>
     * 社員検索件数を取得
     * </pre>
     * 
     * @param employeeId     社員ID
     * @param name           名前
     * @param departmentCode 所属部門コード
     * @param postCode       役職コード
     * @param enteredAtFrom  入社日From
     * @param enteredAtTo    入社日To
     * @return 件数
     */
    int countSearchEmployee(String employeeId, String name, String departmentCode,
            String postCode, LocalDate enteredAtFrom, LocalDate enteredAtTo);

    /**
     * <pre>
     * 社員を登録
     * </pre>
     * 
     * @param employee 登録パラメータ
     * @return 登録結果
     * @throws RuntimeException 社員登録に失敗した場合
     */
    void registerEmployee(EmployeeRegisterRequestDto employee) throws RuntimeException;

    /**
     * <pre>
     * 社員を編集
     * </pre>
     * 
     * @param employee 更新パラメータ
     * @return 更新結果
     * @throws RuntimeException 社員更新に失敗した場合
     */
    void editEmployee(String employeeId, EmployeeEditRequestDto employee) throws RuntimeException;

    /**
     * <pre>
     * 社員を削除
     * </pre>
     * 
     * @param employeeId 社員ID
     * @throws RuntimeException 社員削除に失敗した場合
     */
    void deleteEmployee(String employeeId) throws RuntimeException;

    /**
     * <pre>
     * 社員を有効化
     * </pre>
     * 
     * @param employeeId 社員ID
     * @throws RuntimeException 社員有効化に失敗した場合
     */
    void activeEmployee(String employeeId) throws RuntimeException;

    /**
     * <pre>
     * 社員所属部門情報を取得
     * </pre>
     * 
     * @param employeeId 社員ID
     * @return 社員所属部門情報
     * @throws RuntimeException 社員所属部門情報の取得に失敗した場合
     */
    EmployeeDepartmentEntity getEmployeeDepartment(String employeeId) throws RuntimeException;

}
