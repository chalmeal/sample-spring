package sample.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import sample.model.Employee;

/**
 * <pre>
 * 社員のMapperクラス
 * </pre>
 */
@Component
public class EmployeeMapper implements RowMapper<Employee> {
    private final BeanPropertyRowMapper<Employee> mapper;

    // 初期化
    public EmployeeMapper() {
        this.mapper = new BeanPropertyRowMapper<>(Employee.class);
    }

    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        String sqlType = rs.getString("sql_type");

        switch (sqlType) {
            case "SQL_SELECT_EMPLOYEE":
                return mapper.mapRow(rs, rowNum);
            default:
                return new Employee();
        }
    }

}
