package sample.repository.impl.dao;

import java.time.LocalDate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import sample.context.Pagination;
import sample.context.util.Parse;
import sample.dto.request.employee.EmployeeEditRequestDto;
import sample.dto.request.employee.EmployeeRegisterRequestDto;
import sample.model.Employee;

public class EmployeeDao extends DaoHelper {

    /**
     * <pre>
     * 社員取得SQL
     * </pre>
     * 
     * @param param
     * @param employeeId
     * @return
     */
    protected SqlParamSource getByEmployeeId(MapSqlParameterSource param, String employeeId) {
        String sql = "SELECT "
                + "employee_id, "
                + "name, "
                + "name_kana, "
                + "department_code, "
                + "post_code, "
                + "entered_at, "
                + "mail_address, "
                + "tel_number, "
                + "postal_code, "
                + "address, "
                + "birthday, "
                + "status "
                + "FROM employees "
                + "WHERE employee_id = :employeeId AND status = :status";
        param.addValue("employeeId", employeeId);
        param.addValue("status", Employee.Status.ACTIVE.getCode());

        return new SqlParamSource(sql, param);
    }

    /**
     * <pre>
     * 社員検索SQL
     * </pre>
     * 
     * @param param
     * @param employeeId
     * @param name
     * @param departmentCode
     * @param postCode
     * @param enteredAtFrom
     * @param enteredAtTo
     * @param status
     * @return
     */
    protected SqlParamSource select(MapSqlParameterSource param, String employeeId, String name, String departmentCode,
            String postCode, LocalDate enteredAtFrom, LocalDate enteredAtTo, Integer pageNumber) {
        String sql = "SELECT "
                + "employee_id, "
                + "name, "
                + "name_kana, "
                + "department_code, "
                + "post_code "
                + "FROM employees "
                + "WHERE status = :status ";
        param.addValue("status", Employee.Status.ACTIVE.getCode());

        // 社員ID
        if (StringUtils.isNotEmpty(employeeId)) {
            sql += "AND employee_id = :employeeId ";
            param.addValue("employeeId", employeeId);
        }
        // 名前（部分一致）
        if (StringUtils.isNotEmpty(name)) {
            sql += "AND (name LIKE :name OR name_kana LIKE :name) ";
            param.addValue("name", "%" + name + "%");
        }
        // 部署コード
        if (StringUtils.isNotEmpty(departmentCode)) {
            sql += "AND department_code = :departmentCode ";
            param.addValue("departmentCode", departmentCode);
        }
        // 役職コード
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
        sql += "ORDER BY employee_id ASC "
                + "LIMIT :pageSize OFFSET :pageNumber";
        param.addValue("pageSize", Pagination.DEFAULT_PAGE_SIZE);
        param.addValue("pageNumber", (pageNumber - 1) * Pagination.DEFAULT_PAGE_SIZE);

        return new SqlParamSource(sql, param);
    }

    /**
     * <pre>
     * 社員検索件数取得SQL
     * </pre>
     * 
     * @param param
     * @param employeeId
     * @param name
     * @param departmentCode
     * @param postCode
     * @param enteredAtFrom
     * @param enteredAtTo
     * @return
     */
    protected SqlParamSource countSelect(MapSqlParameterSource param, String employeeId, String name,
            String departmentCode, String postCode, LocalDate enteredAtFrom, LocalDate enteredAtTo) {
        String sql = "SELECT "
                + "COUNT(*) AS count "
                + "FROM employees "
                + "WHERE status = :status ";
        param.addValue("status", Employee.Status.ACTIVE.getCode());

        // 社員ID
        if (StringUtils.isNotEmpty(employeeId)) {
            sql += "AND employee_id = :employeeId ";
            param.addValue("employeeId", employeeId);
        }
        // 名前（部分一致）
        if (StringUtils.isNotEmpty(name)) {
            sql += "AND (name LIKE :name OR name_kana LIKE :name) ";
            param.addValue("name", "%" + name + "%");
        }
        // 部署コード
        if (StringUtils.isNotEmpty(departmentCode)) {
            sql += "AND department_code = :departmentCode ";
            param.addValue("departmentCode", departmentCode);
        }
        // 役職コード
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

        return new SqlParamSource(sql, param);
    }

    /**
     * <pre>
     * 社員登録SQL
     * </pre>
     * 
     * @param param
     * @param employee
     * @return
     */
    protected SqlParamSource insert(MapSqlParameterSource param, EmployeeRegisterRequestDto employee) {
        String sql = "INSERT INTO "
                + "employees ("
                + "employee_id, "
                + "name, "
                + "name_kana, "
                + "department_code, "
                + "post_code, "
                + "entered_at, "
                + "mail_address, "
                + "tel_number, "
                + "postal_code, "
                + "address, "
                + "birthday, "
                + "status, "
                + "created_at, "
                + "updated_at)";
        sql += "VALUES ("
                + ":employeeId, "
                + ":name, "
                + ":nameKana, "
                + ":departmentCode, "
                + ":postCode, "
                + ":enteredAt, "
                + ":mailAddress, "
                + ":telNumber, "
                + ":postalCode, "
                + ":address, "
                + ":birthday, "
                + ":status, "
                + "CURRENT_TIMESTAMP, "
                + "CURRENT_TIMESTAMP"
                + ")";
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

        return new SqlParamSource(sql, param);
    }

    /**
     * <pre>
     * 社員編集SQL
     * </pre>
     * 
     * @param param
     * @param employeeId
     * @param employee
     * @return
     */
    protected SqlParamSource update(MapSqlParameterSource param, String employeeId,
            EmployeeEditRequestDto employee) {
        String sql = "UPDATE "
                + "employees SET "
                + "name = :name, "
                + "name_kana = :nameKana, "
                + "department_code = :departmentCode, "
                + "post_code = :postCode, "
                + "entered_at = :enteredAt, "
                + "mail_address = :mailAddress, "
                + "tel_number = :telNumber, "
                + "postal_code = :postalCode, "
                + "address = :address, "
                + "birthday = :birthday, "
                + "updated_at = CURRENT_TIMESTAMP "
                + "WHERE employee_id = :employeeId "
                + "AND status = :status";
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
        // WHERE
        param.addValue("employeeId", employeeId);
        param.addValue("status", Employee.Status.ACTIVE.getCode());

        return new SqlParamSource(sql, param);
    }

    /**
     * <pre>
     * 社員状態取得SQL
     * </pre>
     * 
     * @param param
     * @param employeeId
     * @return
     */
    protected SqlParamSource getStatusByEmployeeId(MapSqlParameterSource param, String employeeId) {
        String sql = "SELECT "
                + "status "
                + "FROM employees "
                + "WHERE employee_id = :employeeId";
        param.addValue("employeeId", employeeId);

        return new SqlParamSource(sql, param);
    }

    /**
     * <pre>
     * 社員削除SQL
     * </pre>
     * 
     * @param param
     * @param employeeId
     * @return
     */
    protected SqlParamSource delete(MapSqlParameterSource param, String employeeId) {
        String sql = "UPDATE "
                + "employees SET "
                + "status = :status, "
                + "updated_at = CURRENT_TIMESTAMP "
                + "WHERE employee_id = :employeeId "
                + "AND status = :currentStatus";
        param.addValue("status", Employee.Status.INACTIVE.getCode());
        // WHERE
        param.addValue("employeeId", employeeId);
        param.addValue("currentStatus", Employee.Status.ACTIVE.getCode());

        return new SqlParamSource(sql, param);
    }

    /**
     * <pre>
     * 社員有効化SQL
     * </pre>
     * 
     * @param param
     * @param employeeId
     * @return
     */
    protected SqlParamSource active(MapSqlParameterSource param, String employeeId) {
        String sql = "UPDATE "
                + "employees SET "
                + "status = :status, "
                + "updated_at = CURRENT_TIMESTAMP "
                + "WHERE employee_id = :employeeId "
                + "AND status = :currentStatus";
        param.addValue("status", Employee.Status.ACTIVE.getCode());
        // WHERE
        param.addValue("employeeId", employeeId);
        param.addValue("currentStatus", Employee.Status.INACTIVE.getCode());

        return new SqlParamSource(sql, param);
    }

    /**
     * <pre>
     * 社員部署取得SQL
     * </pre>
     * 
     * @param param
     * @param employeeId
     * @return
     */
    protected SqlParamSource getDepartmentByEmployeeId(MapSqlParameterSource param, String employeeId) {
        String sql = "SELECT "
                + "employee_id, "
                + "department_code, "
                + "department_name, "
                + "manager_employee_id "
                + "FROM employee_departments "
                + "WHERE employee_id = :employeeId AND status = :status";
        param.addValue("employeeId", employeeId);
        param.addValue("status", Employee.Status.ACTIVE.getCode());

        return new SqlParamSource(sql, param);
    }

}
