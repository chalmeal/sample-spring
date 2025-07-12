package sample.context.constant.error;

public class EmployeeError extends Error {

    /**
     * <pre>
     * 存在しない社員を取得しようとした場合のエラーコード
     * status: 404
     * message: "社員情報が見つかりませんでした。"
     * </pre>
     */
    public static final String NOT_EXISTS = "1001";

    /**
     * <pre>
     * 既に登録されている社員IDを登録しようとした場合のエラーコード
     * status: 400
     * message: "社員IDまたは社員コードが既に存在します。"
     * </pre>
     */
    public static final String DUPLICATED = "1002";

    /**
     * <pre>
     * 社員のエラーコードを取得
     * </pre>
     * 
     * @param error
     */
    public static String getError(String error) {
        return new StringBuilder()
                .append(ERROR_CODE_EMPLOYEE)
                .append("-")
                .append(error)
                .toString();
    }

}
