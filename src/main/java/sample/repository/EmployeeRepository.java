package sample.repository;

import java.util.Optional;

import sample.dto.ResultDto;
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
    Optional<Employee> getEmployeeById(Integer employeeId);

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
    Optional<Employee[]> searchEmployee(Integer employeeId, String employeeCode, String name, String mail,
            Integer departmentCode);

    /**
     * <p>
     * 社員を登録
     * </p>
     * 
     * @param employee 登録パラメータ
     * @return 登録された社員
     */
    Optional<ResultDto> registerEmployee(EmployeeRegisterRequestDto employee);
}
