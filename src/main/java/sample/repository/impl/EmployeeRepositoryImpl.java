package sample.repository.impl;

import java.time.LocalDate;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import sample.dto.request.employee.EmployeeEditRequestDto;
import sample.dto.request.employee.EmployeeRegisterRequestDto;
import sample.model.Employee.EmployeeDepartmentEntity;
import sample.model.Employee.EmployeeEntity;
import sample.model.Employee.EmployeeSearchEntity;
import sample.repository.EmployeeRepository;
import sample.repository.impl.dao.EmployeeDao;
import sample.repository.impl.mapper.EmployeeMapper;

/**
 * <pre>
 * 社員のRepositoryクラス
 * </pre>
 */
@Repository
public class EmployeeRepositoryImpl extends EmployeeDao implements EmployeeRepository {
    // DI
    // JdbcTemplate
    private final NamedParameterJdbcTemplate jdbcTemplate;

    // 初期化
    public EmployeeRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * {@inheritDoc}
     */
    public EmployeeEntity getEmployeeById(String employeeId) throws RuntimeException {
        MapSqlParameterSource param = new MapSqlParameterSource();
        SqlParamSource source = getByEmployeeId(param, employeeId);

        return jdbcTemplate.queryForObject(source.getSql(), source.getParam(),
                new EmployeeMapper.EmployeeGetMapper());
    }

    /**
     * {@inheritDoc}
     */
    public List<EmployeeSearchEntity> searchEmployee(String employeeId, String name,
            String departmentCode, String postCode, LocalDate enteredAtFrom, LocalDate enteredAtTo,
            Integer pageNumber)
            throws RuntimeException {
        MapSqlParameterSource param = new MapSqlParameterSource();
        SqlParamSource source = this.select(param, employeeId, name,
                departmentCode, postCode, enteredAtFrom, enteredAtTo, pageNumber);

        return jdbcTemplate.query(source.getSql(), source.getParam(),
                new EmployeeMapper.EmployeeSearchMapper());
    }

    /**
     * {@inheritDoc}
     */
    public int countSearchEmployee(String employeeId, String name, String departmentCode,
            String postCode, LocalDate enteredAtFrom, LocalDate enteredAtTo)
            throws RuntimeException {
        MapSqlParameterSource param = new MapSqlParameterSource();
        SqlParamSource source = this.countSelect(param, employeeId, name,
                departmentCode, postCode, enteredAtFrom, enteredAtTo);

        return jdbcTemplate.queryForObject(source.getSql(), source.getParam(),
                Integer.class);
    }

    /**
     * {@inheritDoc}
     */
    public void registerEmployee(EmployeeRegisterRequestDto employee) throws RuntimeException {
        MapSqlParameterSource param = new MapSqlParameterSource();
        SqlParamSource source = this.insert(param, employee);

        jdbcTemplate.update(source.getSql(), source.getParam());
    }

    /**
     * {@inheritDoc}
     */
    public void editEmployee(String employeeId, EmployeeEditRequestDto employee) throws RuntimeException {
        MapSqlParameterSource param = new MapSqlParameterSource();
        SqlParamSource source = this.update(param, employeeId, employee);

        int result = jdbcTemplate.update(source.getSql(), source.getParam());

        if (result == 0) {
            // 更新対象の社員が存在しない場合はエラーを返却
            throw new EmptyResultDataAccessException(result);
        } else {
            // success
        }
    }

    /**
     * {@inheritDoc}
     */
    public int getEmployeeStatus(String employeeId) throws RuntimeException {
        MapSqlParameterSource param = new MapSqlParameterSource();
        SqlParamSource source = getStatusByEmployeeId(param, employeeId);

        return jdbcTemplate.queryForObject(source.getSql(), source.getParam(),
                Integer.class);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteEmployee(String employeeId) throws RuntimeException {
        MapSqlParameterSource param = new MapSqlParameterSource();
        SqlParamSource source = this.delete(param, employeeId);

        int result = jdbcTemplate.update(source.getSql(), source.getParam());
        if (result == 0) {
            // 削除対象の社員が存在しない場合はエラーを返却
            throw new EmptyResultDataAccessException(result);
        } else {
            // success
        }
    }

    /**
     * {@inheritDoc}
     */
    public void activeEmployee(String employeeId) throws RuntimeException {
        MapSqlParameterSource param = new MapSqlParameterSource();
        SqlParamSource source = this.active(param, employeeId);

        int result = jdbcTemplate.update(source.getSql(), source.getParam());
        if (result == 0) {
            // 有効化対象の社員が存在しない場合はエラーを返却
            throw new EmptyResultDataAccessException(result);
        } else {
            // success
        }
    }

    /**
     * {@inheritDoc}
     */
    public EmployeeDepartmentEntity getEmployeeDepartment(String employeeId) throws RuntimeException {
        MapSqlParameterSource param = new MapSqlParameterSource();
        SqlParamSource source = getDepartmentByEmployeeId(param, employeeId);

        return jdbcTemplate.queryForObject(source.getSql(), source.getParam(),
                new EmployeeMapper.EmployeeDepartmentGetMapper());
    }

}