package sample.context.constant.error;

public class EmployeeError extends Error {

    /**
     * <p>
     * 存在しない社員を取得しようとした場合のエラーコード
     * status: 404
     * message: "社員情報が見つかりませんでした。"
     * </p>
     */
    public static final String NOTFOUND = "1001";

    /**
     * <p>
     * 既に登録されている社員IDを登録しようとした場合のエラーコード
     * status: 400
     * message: "社員IDまたは社員コードが既に存在します。"
     * </p>
     */
    public static final String DUPLICATED = "1002";

    /**
     * <p>
     * 社員のエラーコードを取得
     * </p>
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
