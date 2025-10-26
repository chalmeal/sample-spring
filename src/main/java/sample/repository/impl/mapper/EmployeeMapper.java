package sample.repository.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import sample.model.Employee.EmployeeDepartmentEntity;

/**
 * <pre>
 * 社員のMapperクラス
 * </pre>
 */
public class EmployeeMapper {
    /**
     * <pre>
     * 所属部署情報のMapperクラス
     * </pre>
     */
    public static record EmployeeDepartmentMapper() implements RowMapper<EmployeeDepartmentEntity> {
        @Override
        public EmployeeDepartmentEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            return EmployeeDepartmentEntity.builder()
                    .employeeId(rs.getString("employee_id"))
                    .departmentCode(rs.getString("department_code"))
                    .departmentName(rs.getString("department_name"))
                    .managerEmployeeId(rs.getString("manager_employee_id"))
                    .build();
        }
    }
}
