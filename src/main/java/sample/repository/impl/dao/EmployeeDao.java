package sample.repository.impl.dao;

import java.time.LocalDate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import sample.context.Pagination;
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
