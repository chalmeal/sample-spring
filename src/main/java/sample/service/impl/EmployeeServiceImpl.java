package sample.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sample.context.constant.error.EmployeeError;
import sample.context.exception.ServiceException;
import sample.context.util.Message;
import sample.dto.ResultDto;
import sample.dto.ResultDto.ResultType;
import sample.dto.request.employee.EmployeeEditRequestDto;
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
    // Repository
    private final EmployeeRepository repository;
    // Message
    private final Message message;

    /**
     * {@inheritDoc}
     */
    @Override
    public EmployeeResponseDto getEmployee(String employeeId) throws ServiceException {
        EmployeeResponseDto result = new EmployeeResponseDto();

        try {
            // 社員IDから社員を取得
            Optional<Employee> optEmployee = repository.getEmployeeById(employeeId);

            // 社員情報をDTOに設定
            Employee employee = optEmployee.get();
            result.setEmployeeId(employee.getEmployeeId());
            result.setEmployeeCode(employee.getEmployeeCode());
            result.setName(employee.getName());
            result.setNameKana(employee.getNameKana());
            result.setDepartmentCode(employee.getDepartmentCode());
            result.setMail(employee.getMail());
            result.setStatus(employee.getStatus());

            return result;
        } catch (EmptyResultDataAccessException e) {
            // 社員が存在しない場合はエラーを返却
            throw new ServiceException(HttpStatus.NOT_FOUND,
                    EmployeeError.getError(EmployeeError.NOT_EXISTS),
                    message.getMessage("error.employee.notfound"));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EmployeeResponseDto> searchEmployee(String employeeId, String employeeCode, String name, String mail,
            String departmentCode) {
        List<EmployeeResponseDto> result = new ArrayList<>();

        try {
            // 社員を検索
            // 検索条件に一致する社員が存在しない場合は空のリストを返却
            Optional<Employee[]> optEmployee = repository.searchEmployee(employeeId, employeeCode, name, mail,
                    departmentCode);

            // 社員情報をDTOに設定
            Employee[] employees = optEmployee.get();
            for (Employee employee : employees) {
                EmployeeResponseDto dto = new EmployeeResponseDto();
                dto.setEmployeeId(employee.getEmployeeId());
                dto.setEmployeeCode(employee.getEmployeeCode());
                dto.setName(employee.getName());
                dto.setNameKana(employee.getNameKana());
                dto.setDepartmentCode(employee.getDepartmentCode());
                dto.setMail(employee.getMail());
                dto.setStatus(employee.getStatus());

                result.add(dto);
            }

            return result;
        } catch (EmptyResultDataAccessException e) {
            // 社員が存在しない場合は空のリストを返却
            return result;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResultDto registerEmployee(EmployeeRegisterRequestDto param) throws ServiceException {
        try {
            // 社員登録
            repository.registerEmployee(param);

            // 登録結果を返却
            ResultDto result = new ResultDto();
            result.setResult(ResultType.SUCCESS);
            result.setMessage(message.getMessage("success.employee.register"));

            return result;
        } catch (DuplicateKeyException e) {
            // 社員IDが重複している場合はエラーを返却
            throw new ServiceException(HttpStatus.BAD_REQUEST,
                    EmployeeError.getError(EmployeeError.DUPLICATED),
                    message.getMessage("error.employee.register.duplicate"));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResultDto editEmployee(String employeeId, EmployeeEditRequestDto param) throws ServiceException {
        try {
            // 社員編集
            repository.editEmployee(employeeId, param);

            // 更新結果を返却
            ResultDto result = new ResultDto();
            result.setResult(ResultType.SUCCESS);
            result.setMessage(message.getMessage("success.employee.edit"));

            return result;
        } catch (EmptyResultDataAccessException e) {
            // 社員が存在しない場合はエラーを返却
            throw new ServiceException(HttpStatus.NOT_FOUND,
                    EmployeeError.getError(EmployeeError.NOT_EXISTS),
                    message.getMessage("error.employee.notfound"));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResultDto deleteEmployee(String employeeId) throws ServiceException {
        try {
            // 社員削除
            repository.deleteEmployee(employeeId);

            // 削除結果を返却
            ResultDto result = new ResultDto();
            result.setResult(ResultType.SUCCESS);
            result.setMessage(message.getMessage("success.employee.delete"));

            return result;
        } catch (EmptyResultDataAccessException e) {
            // 社員が存在しない場合はエラーを返却
            throw new ServiceException(HttpStatus.NOT_FOUND,
                    EmployeeError.getError(EmployeeError.NOT_EXISTS),
                    message.getMessage("error.employee.notfound"));
        }
    }

}
