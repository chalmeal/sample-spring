package sample.repository.sql;

/**
 * <pre>
 * 社員のSQLクラス
 * SQLのパラメタがnullableの場合は、repository内で動的SQLを組み立ててください。
 * </pre>
 */
public class EmployeeSql {

        /**
         * <pre>
         * 社員取得SQL
         * </pre>
         */
        protected final String SQL_GET_EMPLOYEE_BY_ID = " SELECT "
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
                        + "created_at, "
                        + "updated_at, "
                        + "status "
                        + "FROM employees "
                        + "WHERE employee_id = :employeeId AND status = :status";

        protected final String SQL_SELECT_EMPLOYEE = " SELECT "
                        + "'SQL_SELECT_EMPLOYEE' AS sql_type, "
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
                        + "created_at, "
                        + "updated_at, "
                        + "status "
                        + "FROM employees ";

        /**
         * <pre>
         * 社員登録SQL
         * </pre>
         * 
         * @param employeeId     社員ID
         * @param name           名前
         * @param nameKana       名前カナ
         * @param departmentCode 所属部門コード
         * @param postCode       役職コード
         * @param enteredAt      入社年月日
         * @param mailAddress    メールアドレス
         * @param telNumber      電話番号
         * @param postalCode     郵便番号
         * @param address        住所
         * @param birthday       生年月日
         * @param status         状態
         */
        protected final String SQL_INSERT_EMPLOYEE = "INSERT INTO employees "
                        + "(employee_id, name, name_kana, department_code, post_code, entered_at, "
                        + "mail_address, tel_number, postal_code, address, birthday, created_at, updated_at, status) "
                        + "VALUES (:employeeId, :name, :nameKana, :departmentCode, :postCode, :enteredAt, "
                        + ":mailAddress, :telNumber, :postalCode, :address, :birthday, NOW(), NOW(), :status)";

        /**
         * <pre>
         * 社員編集SQL
         * </pre>
         * 
         * @param name           名前
         * @param nameKana       名前カナ
         * @param departmentCode 所属部門コード
         * @param postCode       役職コード
         * @param enteredAt      入社年月日
         * @param mail           メールアドレス
         * @param telNumber      電話番号
         * @param postalCode     郵便番号
         * @param address        住所
         * @param birthday       生年月日
         */
        protected final String SQL_UPDATE_EMPLOYEE = "UPDATE employees "
                        + "SET name = :name, "
                        + "name_kana = :nameKana, "
                        + "department_code = :departmentCode, "
                        + "post_code = :postCode, "
                        + "entered_at = :enteredAt, "
                        + "mail_address = :mailAddress, "
                        + "tel_number = :telNumber, "
                        + "postal_code = :postalCode, "
                        + "address = :address, "
                        + "birthday = :birthday "
                        + "WHERE employee_id = :employeeId";

        /**
         * <pre>
         * 社員削除SQL
         * </pre>
         * 
         * @param employeeId 社員ID
         */
        protected final String SQL_DELETE_EMPLOYEE = "UPDATE employees "
                        + "SET status = :status "
                        + "WHERE employee_id = :employeeId";

        /**
         * <pre>
         * 社員有効化SQL
         * </pre>
         * 
         * @param employeeId 社員ID
         */
        protected final String SQL_ACTIVE_EMPLOYEE = "UPDATE employees "
                        + "SET status = :status "
                        + "WHERE employee_id = :employeeId";

        /**
         * <pre>
         * 社員所属部門情報取得SQL
         * </pre>
         */
        protected final String SQL_GET_EMPLOYEE_DEPARTMENT = "SELECT "
                        + "e.employee_id, "
                        + "e.department_code, "
                        + "d.department_name, "
                        + "d.manager_employee_id "
                        + "FROM employees e "
                        + "JOIN departments d ON e.department_code = d.department_code "
                        + "WHERE e.employee_id = :employeeId AND e.status = 1";

}
