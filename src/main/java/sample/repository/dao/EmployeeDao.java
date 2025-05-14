package sample.repository.dao;

import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import sample.model.Employee;
import sample.model.mapper.EmployeeMapper;

/**
 * <p>
 * 社員のDao
 * </p>
 */
@Repository
public class EmployeeDao extends DaoHelper {
    // DI
    // JdbcTenplate
    private final NamedParameterJdbcTemplate jdbcTemplate;
    // Mapper
    private final EmployeeMapper mapper;

    // 初期化
    public EmployeeDao(DataSource dataSource, EmployeeMapper mapper) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.mapper = mapper;
    }

    // 社員取得SQL
    private static final String SQL_SELECT_EMPLOYEE = " SELECT "
            + "'SQL_SELECT_EMPLOYEE' AS sql_type, "
            + "employee_id, "
            + "employee_code, "
            + "name, "
            + "name_kana, "
            + "mail, "
            + "department_code, "
            + "status "
            + "FROM employees ";

    /**
     * <p>
     * 社員IDから社員を取得
     * </p>
     * 
     * @param employeeId
     * @return 社員
     */
    public Optional<Employee> getEmployeeById(int employeeId) {
        try {
            StringBuilder sql = new StringBuilder()
                    .append(SQL_SELECT_EMPLOYEE)
                    .append(SQL_WHERE).append("employee_id = :employeeId");
            MapSqlParameterSource param = new MapSqlParameterSource()
                    .addValue("employeeId", employeeId);

            Employee employee = jdbcTemplate.queryForObject(sql.toString(), param, mapper);

            return Optional.ofNullable(employee);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
