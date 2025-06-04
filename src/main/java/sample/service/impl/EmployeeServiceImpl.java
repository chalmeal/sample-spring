package sample.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sample.context.constant.ResponseConst;
import sample.context.exception.ServiceException;
import sample.dto.ResultDto;
import sample.dto.request.employee.EmployeeRegisterRequestDto;
import sample.dto.response.EmployeeResponseDto;
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

        Optional<Employee> optEmployee = repository.getEmployeeById(employeeId);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EmployeeResponseDto> searchEmployee(Integer employeeId, String employeeCode, String name, String mail,
            Integer departmentCode) {
        List<EmployeeResponseDto> result = new ArrayList<>();

        Optional<Employee[]> optEmployee = repository.searchEmployee(employeeId, employeeCode, name, mail,
                departmentCode);

        Employee[] employees = optEmployee.get();
        for (Employee employee : employees) {
            EmployeeResponseDto dto = new EmployeeResponseDto();
            dto.setEmployeeId(employee.getEmployeeId());
            dto.setName(employee.getName());
            dto.setNameKana(employee.getNameKana());
            dto.setDepartmentCode(employee.getDepartmentCode());
            dto.setMail(employee.getMail());
            dto.setStatus(employee.getStatus());

            result.add(dto);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResultDto registerEmployee(EmployeeRegisterRequestDto param) {
        Optional<ResultDto> result = repository.registerEmployee(param);
        result.orElseThrow(() -> {
            return new ServiceException(ResponseConst.Status.BAD_REQUEST.getStatus(),
                    ResponseConst.Error.EMPLOYEE_REGISTER_EXIST_ID);
        });

        return result.get();
    }

}
