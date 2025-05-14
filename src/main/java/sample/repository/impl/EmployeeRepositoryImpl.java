package sample.repository.impl;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import sample.model.Employee;
import sample.repository.EmployeeRepository;
import sample.repository.dao.EmployeeDao;

/**
 * <p>
 * 社員のRepository実装クラス
 * </p>
 */
@Repository
@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepository {

    // DI
    private final EmployeeDao dao;

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Employee> getEmployee(int employeeId) {
        return dao.getEmployeeById(employeeId);
    }

}
