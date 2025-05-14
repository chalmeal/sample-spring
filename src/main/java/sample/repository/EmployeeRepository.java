package sample.repository;

import java.util.Optional;

import sample.model.Employee;

/**
 * <p>
 * 社員のRepositoryインターフェース
 * 
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
    Optional<Employee> getEmployee(int employeeId);

}
