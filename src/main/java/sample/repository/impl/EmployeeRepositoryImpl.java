package sample.repository.impl;

import java.time.LocalDate;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import sample.context.util.Parse;
import sample.dto.request.employee.EmployeeEditRequestDto;
import sample.dto.request.employee.EmployeeRegisterRequestDto;
import sample.model.Employee;
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

        String sql = "SQL_INSERT_EMPLOYEE";
        param.addValue("employeeId", employee.getEmployeeId());
        param.addValue("name", employee.getName());
        param.addValue("nameKana", employee.getNameKana());
        param.addValue("departmentCode", employee.getDepartmentCode());
        param.addValue("postCode", employee.getPostCode());
        param.addValue("enteredAt", Parse.parseDate(employee.getEnteredAt()));
        param.addValue("mailAddress", employee.getMailAddress());
        param.addValue("telNumber", employee.getTelNumber());
        param.addValue("postalCode", employee.getPostalCode());
        param.addValue("address", employee.getAddress());
        param.addValue("birthday", Parse.parseDate(employee.getBirthday()));
        param.addValue("status", Employee.Status.ACTIVE.getCode());

        jdbcTemplate.update(sql, param);
    }

    /**
     * {@inheritDoc}
     */
    public void editEmployee(String employeeId, EmployeeEditRequestDto employee) throws RuntimeException {
        MapSqlParameterSource param = new MapSqlParameterSource();

        String sql = "SQL_UPDATE_EMPLOYEE";
        param.addValue("name", employee.getName());
        param.addValue("nameKana", employee.getNameKana());
        param.addValue("departmentCode", employee.getDepartmentCode());
        param.addValue("postCode", employee.getPostCode());
        param.addValue("enteredAt", employee.getEnteredAt());
        param.addValue("mailAddress", employee.getMailAddress());
        param.addValue("telNumber", employee.getTelNumber());
        param.addValue("postalCode", employee.getPostalCode());
        param.addValue("address", employee.getAddress());
        param.addValue("birthday", employee.getBirthday());
        // WHERE句で指定する社員ID
        param.addValue("employeeId", employeeId);

        int result = jdbcTemplate.update(sql, param);
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
    public void deleteEmployee(String employeeId) throws RuntimeException {
        MapSqlParameterSource param = new MapSqlParameterSource();

        String sql = "SQL_DELETE_EMPLOYEE";
        // WHERE句で指定する社員ID
        param.addValue("status", Employee.Status.INACTIVE.getCode());
        param.addValue("employeeId", employeeId);

        int result = jdbcTemplate.update(sql, param);
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

        String sql = "SQL_ACTIVE_EMPLOYEE";
        param.addValue("status", Employee.Status.ACTIVE.getCode());
        param.addValue("employeeId", employeeId);

        int result = jdbcTemplate.update(sql, param);
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