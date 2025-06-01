package sample.repository.sql;

/**
 * <p>
 * 社員のSQLクラス
 * SQLのパラメタがnullableの場合は、repository内で動的SQLを組み立ててください。
 * </p>
 */
public class EmployeeSql {

        /**
         * <p>
         * 社員取得SELECT
         * </p>
         */
        protected final String SQL_SELECT_EMPLOYEE = " SELECT "
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
        protected final String SQL_GET_EMPLOYEE_BY_ID = SQL_SELECT_EMPLOYEE
                        + "WHERE employee_id = :employeeId";

}
