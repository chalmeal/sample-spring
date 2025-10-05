package sample.repository.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import sample.context.util.Parse;
import sample.dto.request.employee.EmployeeEditRequestDto;
import sample.dto.request.employee.EmployeeRegisterRequestDto;
import sample.model.Employee;
import sample.model.mapper.EmployeeMapper;
import sample.repository.EmployeeRepository;
import sample.repository.sql.EmployeeSql;

/**
 * <pre>
 * 社員のRepositoryクラス
 * 
 * <pre>
 */
@Repository
public class EmployeeRepositoryImpl extends EmployeeSql implements EmployeeRepository {
    // DI
    // JdbcTemplate
    private final NamedParameterJdbcTemplate jdbcTemplate;
    // Mapper
    private final EmployeeMapper mapper;

    // 初期化
    public EmployeeRepositoryImpl(DataSource dataSource, EmployeeMapper mapper) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.mapper = mapper;
    }

    /**
     * <pre>
     * 社員IDから社員を取得
     * </pre>
     * 
     * @param employeeId 社員ID
     * @return 社員
     */
    public Optional<Employee> getEmployeeById(String employeeId) throws RuntimeException {
        MapSqlParameterSource param;
        param = new MapSqlParameterSource();

        String sql = SQL_GET_EMPLOYEE_BY_ID;
        param.addValue("employeeId", employeeId);

        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, param, mapper));
    }

    /**
     * <pre>
     * 社員を検索
     * </pre>
     * 
     * @param employeeId     社員ID
     * @param name           名前
     * @param departmentCode 所属部門コード
     * @param postCode       役職コード
     * @param mail           メールアドレス
     * @param status         状態
     * @return 社員
     */
    public Optional<Employee[]> searchEmployee(String employeeId, String name,
            String departmentCode, String postCode, LocalDate enteredAtFrom, LocalDate enteredAtTo, String status)
            throws RuntimeException {
        MapSqlParameterSource param;
        param = new MapSqlParameterSource();

        String sql = SQL_SELECT_EMPLOYEE;
        sql += "WHERE 1=1 ";

        // 社員ID
        if (StringUtils.isNotEmpty(employeeId)) {
            sql += "AND employee_id = :employeeId ";
            param.addValue("employeeId", employeeId);
        }
        // 名前
        if (StringUtils.isNotEmpty(name)) {
            sql += "AND (name LIKE :name OR name_kana LIKE :name) ";
            param.addValue("name", "%" + name + "%");
        }
        // 所属部門
        if (StringUtils.isNotEmpty(departmentCode)) {
            sql += "AND department_code = :departmentCode ";
            param.addValue("departmentCode", departmentCode);
        }
        // 役職
        if (StringUtils.isNotEmpty(postCode)) {
            sql += "AND post_code = :postCode ";
            param.addValue("postCode", postCode);
        }
        // 入社年月日
        if (enteredAtFrom != null && enteredAtTo != null) {
            sql += "AND entered_at BETWEEN :enteredAtFrom AND :enteredAtTo ";
            param.addValue("enteredAtFrom", enteredAtFrom);
            param.addValue("enteredAtTo", enteredAtTo);
        } else if (enteredAtFrom != null) {
            // Fromのみ
            sql += "AND entered_at >= :enteredAtFrom ";
            param.addValue("enteredAtFrom", enteredAtFrom);
        } else if (enteredAtTo != null) {
            // Toのみ
            sql += "AND entered_at <= :enteredAtTo ";
            param.addValue("enteredAtTo", enteredAtTo);
        }
        // 状態
        if (StringUtils.isNotEmpty(status)) {
            sql += "AND status = :status ";
            param.addValue("status", Integer.parseInt(status));
        }
        sql += "ORDER BY entered_at desc, employee_id ";

        List<Employee> employees = jdbcTemplate.query(sql, param, mapper);

        return Optional.ofNullable(employees.toArray(new Employee[employees.size()]));
    }

    /**
     * <pre>
     * 社員を登録
     * </pre>
     * 
     * @param employee 登録する社員
     * @return 登録結果
     * @throws RuntimeException 社員登録に失敗した場合
     */
    public void registerEmployee(EmployeeRegisterRequestDto employee) throws RuntimeException {
        MapSqlParameterSource param;
        param = new MapSqlParameterSource();

        String sql = SQL_INSERT_EMPLOYEE;

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
     * <pre>
     * 社員を編集
     * </pre>
     * 
     * @param employee 更新する社員
     * @return 更新結果
     * @throws RuntimeException 社員更新に失敗した場合
     */
    public void editEmployee(String employeeId, EmployeeEditRequestDto employee) throws RuntimeException {
        MapSqlParameterSource param;
        param = new MapSqlParameterSource();

        String sql = SQL_UPDATE_EMPLOYEE;

        param.addValue("name", employee.getName());
        param.addValue("nameKana", employee.getNameKana());
        param.addValue("mail", employee.getMail());
        param.addValue("departmentCode", employee.getDepartmentCode());
        param.addValue("status", employee.getStatus());
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
     * <pre>
     * 社員を物理削除
     * </pre>
     * 
     * @param employeeId 社員ID
     * @return 削除結果
     * @throws RuntimeException 社員削除に失敗した場合
     */
    public void deleteEmployee(String employeeId) throws RuntimeException {
        MapSqlParameterSource param;
        param = new MapSqlParameterSource();

        String sql = SQL_DELETE_EMPLOYEE;
        // WHERE句で指定する社員ID
        param.addValue("employeeId", employeeId);

        int result = jdbcTemplate.update(sql, param);
        if (result == 0) {
            // 削除対象の社員が存在しない場合はエラーを返却
            throw new EmptyResultDataAccessException(result);
        } else {
            // success
        }
    }

}
