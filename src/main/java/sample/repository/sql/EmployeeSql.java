package sample.repository.sql;

/**
 * <p>
 * 社員のSQLクラス
 * </p>
 */
public class EmployeeSql extends SqlHelper {

    /**
     * <p>
     * 社員取得SELECT
     * </p>
     */
    private final String SQL_SELECT_EMPLOYEE = " SELECT "
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
     * 社員取得SQL
     * </p>
     *
     * @param employeeId 社員ID
     */
    protected final String SQL_SELECT_EMPLOYEE_BY_ID = SQL_SELECT_EMPLOYEE
            + SQL_WHERE + "employee_id = :employeeId";
}
