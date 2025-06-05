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

        /**
         * <p>
         * 社員登録INSERT
         * </p>
         * 
         * @param employeeId     社員ID
         * @param employeeCode   社員コード
         * @param name           名前
         * @param nameKana       名前カナ
         * @param mail           メールアドレス
         * @param departmentCode 所属部門コード
         * @param status         状態
         */
        protected final String SQL_INSERT_EMPLOYEE = "INSERT INTO employees "
                        + "(employee_id, employee_code, name, name_kana, mail, department_code, status) "
                        + "VALUES (:employeeId, :employeeCode, :name, :nameKana, :mail, :departmentCode, :status)";

}
