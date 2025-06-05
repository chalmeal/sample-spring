package sample.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import sample.model.Employee;

/**
 * <p>
 * 社員のMapperクラス
 * </p>
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
                return getEmployee(rs, rowNum);
            default:
                return new Employee();
        }
    }

    /**
     * <p>
     * 社員取得SQLマッピング
     * </p>
     * 
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    private Employee getEmployee(ResultSet rs, int rowNum) throws SQLException {
        Employee model = mapper.mapRow(rs, rowNum);
        model.setEmployeeId(rs.getString("employee_id"));
        model.setEmployeeCode(rs.getString("employee_code"));
        model.setName(rs.getString("name"));
        model.setNameKana(rs.getString("name_kana"));
        model.setMail(rs.getString("mail"));
        model.setDepartmentCode(rs.getString("department_code"));
        model.setStatus(rs.getInt("status"));

        return model;
    }

}
