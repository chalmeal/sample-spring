package sample.repository.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;

import sample.model.Employee.EmployeeDepartmentEntity;
import sample.model.Employee.EmployeeEntity;

/**
 * <pre>
 * 社員のMapperクラス
 * </pre>
 */
public class EmployeeMapper {
    /**
     * <pre>
     * 社員情報のMapperクラス
     * </pre>
     */
    public static record EmployeeGetMapper() implements RowMapper<EmployeeEntity> {
        @Override
        public EmployeeEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            return EmployeeEntity.builder()
                    .employeeId(rs.getString("employee_id"))
                    .name(rs.getString("name"))
                    .nameKana(rs.getString("name_kana"))
                    .departmentCode(rs.getString("department_code"))
                    .postCode(rs.getString("post_code"))
                    .enteredAt(rs.getObject("entered_at", LocalDate.class))
                    .mailAddress(rs.getString("mail_address"))
                    .telNumber(rs.getString("tel_number"))
                    .postalCode(rs.getString("postal_code"))
                    .address(rs.getString("address"))
                    .birthday(rs.getObject("birthday", LocalDate.class))
                    .status(rs.getInt("status"))
                    .build();
        }
    }

    /**
     * <pre>
     * 所属部署情報のMapperクラス
     * </pre>
     */
    public static record EmployeeDepartmentGetMapper() implements RowMapper<EmployeeDepartmentEntity> {
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
