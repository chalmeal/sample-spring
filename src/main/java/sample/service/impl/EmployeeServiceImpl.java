package sample.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sample.context.Pagination;
import sample.context.constant.error.EmployeeError;
import sample.context.exception.ServiceException;
import sample.context.logger.Logger.Loggable;
import sample.context.util.Message;
import sample.dto.ResultDto;
import sample.dto.ResultDto.ResultType;
import sample.dto.request.employee.EmployeeEditRequestDto;
import sample.dto.request.employee.EmployeeRegisterRequestDto;
import sample.dto.response.employee.EmployeeDepartmentResponseDto;
import sample.dto.response.employee.EmployeeResponseDto;
import sample.dto.response.employee.EmployeeSearchResponseDto;
import sample.model.Employee;
import sample.model.Employee.EmployeeDepartmentEntity;
import sample.model.Employee.EmployeeEntity;
import sample.model.Employee.EmployeeSearchEntity;
import sample.repository.EmployeeRepository;
import sample.service.EmployeeService;

/**
 * <pre>
 * 社員のService実装クラス
 * </pre>
 */
@Service
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
    @Transactional(readOnly = true)
    public EmployeeResponseDto getEmployee(String employeeId) throws ServiceException {
        EmployeeResponseDto dto = new EmployeeResponseDto();
        try {
            // 社員IDから社員を取得
            EmployeeEntity result = repository.getEmployeeById(employeeId);
            // 社員情報をDTOに設定
            dto.setEmployeeId(result.employeeId());
            dto.setName(result.name());
            dto.setNameKana(result.nameKana());
            dto.setDepartmentCode(result.departmentCode());
            dto.setPostCode(result.postCode());
            dto.setEnteredAt(result.enteredAt());
            dto.setMailAddress(result.mailAddress());
            dto.setTelNumber(result.telNumber());
            dto.setPostalCode(result.postalCode());
            dto.setAddress(result.address());
            dto.setBirthday(result.birthday());
            dto.setStatus(result.status());

            return dto;
        } catch (EmptyResultDataAccessException e) {
            // 社員が存在しない場合はエラーを返却
            throw new ServiceException(HttpStatus.NOT_FOUND,
                    EmployeeError.NOT_EXISTS,
                    message.get("error.employee.not_exists"));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Pagination<EmployeeSearchResponseDto> searchEmployee(String employeeId, String name,
            String departmentCode, String postCode, LocalDate enteredAtFrom, LocalDate enteredAtTo,
            Integer pageNumber) throws ServiceException {
        List<EmployeeSearchResponseDto> data = new ArrayList<>();
        Pagination<EmployeeSearchResponseDto> page = new Pagination<>();

        try {
            // 社員を検索
            List<EmployeeSearchEntity> result = repository.searchEmployee(employeeId, name, departmentCode, postCode,
                    enteredAtFrom, enteredAtTo, pageNumber);

            // 社員情報をDTOに設定
            for (EmployeeSearchEntity employee : result) {
                EmployeeSearchResponseDto dto = new EmployeeSearchResponseDto();
                dto.setEmployeeId(employee.employeeId());
                dto.setName(employee.name());
                dto.setNameKana(employee.nameKana());
                dto.setDepartmentCode(employee.departmentCode());
                dto.setPostCode(employee.postCode());

                data.add(dto);
            }
            // 検索件数を取得
            int rowCount = repository.countSearchEmployee(employeeId, name, departmentCode, postCode,
                    enteredAtFrom, enteredAtTo);

            return page.paging(data, rowCount);
        } catch (EmptyResultDataAccessException e) {
            // 社員が存在しない場合は空のリストを返却
            return page.paging(Collections.emptyList(), 0);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    @Loggable(category = "API")
    public ResultDto registerEmployee(EmployeeRegisterRequestDto param) throws ServiceException {
        try {
            // 社員登録
            repository.registerEmployee(param);

            // 登録結果を返却
            ResultDto result = new ResultDto();
            result.setResult(ResultType.SUCCESS);
            result.setMessage(message.get("success.employee.register"));

            return result;
        } catch (DuplicateKeyException e) {
            // 社員IDが重複している場合はエラーを返却
            throw new ServiceException(HttpStatus.BAD_REQUEST,
                    EmployeeError.DUPLICATED,
                    message.get("error.employee.register.duplicate"));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    @Loggable(category = "API")
    public ResultDto editEmployee(String employeeId, EmployeeEditRequestDto param) throws ServiceException {
        try {
            // 社員編集
            repository.editEmployee(employeeId, param);

            // 更新結果を返却
            ResultDto result = new ResultDto();
            result.setResult(ResultType.SUCCESS);
            result.setMessage(message.get("success.employee.edit"));

            return result;
        } catch (EmptyResultDataAccessException e) {
            // 社員が存在しない場合はエラーを返却
            throw new ServiceException(HttpStatus.NOT_FOUND,
                    EmployeeError.NOT_EXISTS,
                    message.get("error.employee.not_exists"));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    @Loggable(category = "API")
    public ResultDto deleteEmployee(String employeeId) throws ServiceException {
        try {
            // 社員状態取得
            int status = repository.getEmployeeStatus(employeeId);
            if (status == Employee.Status.INACTIVE.getCode()) {
                // 既に無効化されている場合はエラーを返却
                throw new ServiceException(HttpStatus.BAD_REQUEST,
                        EmployeeError.ALREADY_INACTIVE,
                        message.get("error.employee.already_inactive"));
            }
            // 社員削除
            repository.deleteEmployee(employeeId);

            // 削除結果を返却
            ResultDto result = new ResultDto();
            result.setResult(ResultType.SUCCESS);
            result.setMessage(message.get("success.employee.delete"));

            return result;
        } catch (EmptyResultDataAccessException e) {
            // 社員が存在しない場合はエラーを返却
            throw new ServiceException(HttpStatus.NOT_FOUND,
                    EmployeeError.NOT_EXISTS,
                    message.get("error.employee.not_exists"));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    @Loggable(category = "API")
    public ResultDto activeEmployee(String employeeId) throws ServiceException {
        try {
            // 社員状態取得
            int status = repository.getEmployeeStatus(employeeId);
            if (status == Employee.Status.ACTIVE.getCode()) {
                // 既に有効化されている場合はエラーを返却
                throw new ServiceException(HttpStatus.BAD_REQUEST,
                        EmployeeError.ALREADY_ACTIVE,
                        message.get("error.employee.already_active"));
            }
            // 社員有効化
            repository.activeEmployee(employeeId);

            // 有効化結果を返却
            ResultDto result = new ResultDto();
            result.setResult(ResultType.SUCCESS);
            result.setMessage(message.get("success.employee.active"));

            return result;
        } catch (EmptyResultDataAccessException e) {
            // 社員が存在しない場合はエラーを返却
            throw new ServiceException(HttpStatus.NOT_FOUND,
                    EmployeeError.NOT_EXISTS,
                    message.get("error.employee.not_exists"));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public EmployeeDepartmentResponseDto getEmployeeDepartment(String employeeId) throws ServiceException {
        EmployeeDepartmentResponseDto dto = new EmployeeDepartmentResponseDto();
        try {
            EmployeeDepartmentEntity result = repository.getEmployeeDepartment(employeeId);
            dto.setEmployeeId(result.employeeId());
            dto.setDepartmentCode(result.departmentCode());
            dto.setDepartmentName(result.departmentName());
            dto.setManagerEmployeeId(result.managerEmployeeId());

            return dto;
        } catch (EmptyResultDataAccessException e) {
            // 社員が存在しない場合はエラーを返却
            throw new ServiceException(HttpStatus.NOT_FOUND,
                    EmployeeError.NOT_EXISTS,
                    message.get("error.employee.not_exists"));
        }
    }

}
