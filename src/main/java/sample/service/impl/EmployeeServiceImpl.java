package sample.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sample.constant.ResponseConst;
import sample.dto.response.EmployeeResponseDto;
import sample.exception.ServiceException;
import sample.model.Employee;
import sample.repository.EmployeeRepository;
import sample.service.EmployeeService;

/**
 * <p>
 * 社員のService実装クラス
 * </p>
 */
@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    // DI
    private final EmployeeRepository repository;

    /**
     * {@inheritDoc}
     */
    @Override
    public EmployeeResponseDto getEmployee(int employeeId) {
        EmployeeResponseDto result = new EmployeeResponseDto();

        Optional<Employee> optEmployee = repository.getEmployee(employeeId);
        optEmployee.orElseThrow(() -> {
            return new ServiceException(ResponseConst.Status.NOT_FOUND.getStatus(),
                    ResponseConst.Error.EMPLOYEE_GET_NOT_FOUND);
        });

        Employee employee = optEmployee.get();
        result.setEmployeeId(employee.getEmployeeId());
        result.setName(employee.getName());
        result.setNameKana(employee.getNameKana());
        result.setDepartmentCode(employee.getDepartmentCode());
        result.setMail(employee.getMail());
        result.setStatus(employee.getStatus());

        return result;
    }

}
