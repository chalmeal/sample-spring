package sample.repository;

import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import sample.model.Employee;
import sample.model.mapper.EmployeeMapper;
import sample.repository.sql.EmployeeSql;

/**
 * <p>
 * 社員のRepositoryクラス
 * <p>
 */
@Repository
@RequiredArgsConstructor
public class EmployeeRepository extends EmployeeSql {

    // DI
    // JdbcTenplate
    private final NamedParameterJdbcTemplate jdbcTemplate;
    // Mapper
    private final EmployeeMapper mapper;

    // 初期化
    public EmployeeRepository(DataSource dataSource, EmployeeMapper mapper) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.mapper = mapper;
    }

    /**
     * <p>
     * 社員IDから社員を取得
     * </p>
     * 
     * @param employeeId 社員ID
     * @return 社員
     */
    public Optional<Employee> getEmployeeById(int employeeId) {
        try {
            StringBuilder sql = new StringBuilder()
                    .append(SQL_SELECT_EMPLOYEE_BY_ID);
            MapSqlParameterSource param = new MapSqlParameterSource()
                    .addValue("employeeId", employeeId);

            Employee employee = jdbcTemplate.queryForObject(sql.toString(), param, mapper);

            return Optional.ofNullable(employee);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

}
