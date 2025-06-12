package sample.repository;

import java.util.Optional;

import sample.dto.request.employee.EmployeeEditRequestDto;
import sample.dto.request.employee.EmployeeRegisterRequestDto;
import sample.model.Employee;

/**
 * <p>
 * 社員のRepositoryインターフェース
 * <p>
 */
public interface EmployeeRepository {

    /**
     * <p>
     * 社員IDから社員を取得
     * </p>
     * 
     * @param employeeId 社員ID
     * @return 社員
     */
    Optional<Employee> getEmployeeById(String employeeId);

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
     * @return 社員
     */
    Optional<Employee[]> searchEmployee(String employeeId, String employeeCode, String name, String mail,
            String departmentCode);

    /**
     * <p>
     * 社員を登録
     * </p>
     * 
     * @param employee 登録パラメータ
     * @return 登録結果
     * @throws RuntimeException 社員登録に失敗した場合
     */
    void registerEmployee(EmployeeRegisterRequestDto employee) throws RuntimeException;

    /**
     * <p>
     * 社員を編集
     * </p>
     * 
     * @param employee 更新パラメータ
     * @return 更新結果
     * @throws RuntimeException 社員更新に失敗した場合
     */
    void editEmployee(String employeeId, EmployeeEditRequestDto employee) throws RuntimeException;
}
