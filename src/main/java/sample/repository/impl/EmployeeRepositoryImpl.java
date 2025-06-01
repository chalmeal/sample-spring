package sample.repository.impl;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import sample.model.Employee;
import sample.model.mapper.EmployeeMapper;
import sample.repository.EmployeeRepository;
import sample.repository.sql.EmployeeSql;

/**
 * <p>
 * 社員のRepositoryクラス
 * <p>
 */
@Repository
public class EmployeeRepositoryImpl extends EmployeeSql implements EmployeeRepository {
    // DI
    // JdbcTenplate
    private final NamedParameterJdbcTemplate jdbcTemplate;
    // Mapper
    private final EmployeeMapper mapper;

    // 初期化
    public EmployeeRepositoryImpl(DataSource dataSource, EmployeeMapper mapper) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.mapper = mapper;
    }

    /**
     * <p>
     * 社員IDから社員を取得
     * </p>
     * 
     * @param employeeId 社員ID
     * 
     * @return 社員
     */
    public Optional<Employee> getEmployeeById(Integer employeeId) {
        MapSqlParameterSource param;
        param = new MapSqlParameterSource();

        try {
            String sql = SQL_GET_EMPLOYEE_BY_ID;
            param.addValue("employeeId", employeeId);

            Employee employee = jdbcTemplate.queryForObject(sql, param, mapper);

            return Optional.ofNullable(employee);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

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
     * 
     * @return 社員
     */
    public Optional<Employee[]> searchEmployee(Integer employeeId, String employeeCode, String name, String mail,
            Integer departmentCode) {
        MapSqlParameterSource param;
        param = new MapSqlParameterSource();

        try {
            String sql = SQL_SELECT_EMPLOYEE;
            sql += "WHERE 1=1 ";

            // 社員ID
            if (employeeId != null) {
                sql += "AND employee_id = :employeeId ";
                param.addValue("employeeId", employeeId);
            }
            // 社員コード
            if (!employeeCode.isEmpty()) {
                sql += "AND employee_code LIKE :employeeCode ";
                param.addValue("employeeCode", "%" + employeeCode + "%");
            }
            // 名前
            if (!name.isEmpty()) {
                sql += "AND (name LIKE :name OR name_kana LIKE :name) ";
                param.addValue("name", "%" + name + "%");
            }
            // メールアドレス
            if (!mail.isEmpty()) {
                sql += "AND mail LIKE :mail ";
                param.addValue("mail", "%" + mail + "%");
            }
            // 所属部門コード
            if (departmentCode != null) {
                sql += "AND department_code = :departmentCode ";
                param.addValue("departmentCode", departmentCode);
            }
            sql += "ORDER BY employee_id ";

            List<Employee> employees = jdbcTemplate.query(sql, param, mapper);

            return Optional.ofNullable(employees.toArray(new Employee[employees.size()]));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

}
