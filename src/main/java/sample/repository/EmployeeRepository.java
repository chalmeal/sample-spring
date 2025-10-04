package sample.repository;

import java.time.LocalDate;
import java.util.Optional;

import sample.dto.request.employee.EmployeeEditRequestDto;
import sample.dto.request.employee.EmployeeRegisterRequestDto;
import sample.model.Employee;

/**
 * <pre>
 * 社員のRepositoryインターフェース
 * 
 * <pre>
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
    Optional<Employee> getEmployeeById(String employeeId);

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
     * @return 社員
     */
    Optional<Employee[]> searchEmployee(String employeeId, String name,
            String departmentCode, String postCode, LocalDate enteredAtFrom, LocalDate enteredAtTo, String status);

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

}
