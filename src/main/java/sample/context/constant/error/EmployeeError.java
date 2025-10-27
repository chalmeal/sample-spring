package sample.context.constant.error;

/**
 * <pre>
 * 社員に関するエラーコード定数クラス
 * 子エラーコードは1000番台とします。
 * </pre>
 */
public class EmployeeError extends Error {

    /**
     * <pre>
     * 既に登録されている社員IDを登録しようとした場合のエラーコード
     * status: 400
     * message: "社員IDまたはメールアドレスが既に存在します。"
     * </pre>
     */
    public static final String DUPLICATED = BAD_REQUEST + "1001";

    /**
     * <pre>
     * 既に無効化されている社員を無効化しようとした場合のエラーコード
     * status: 400
     * message: "社員は既に無効化されています。"
     * </pre>
     */
    public static final String ALREADY_INACTIVE = BAD_REQUEST + "1002";

    /**
     * <pre>
     * 既に有効化されている社員を有効化しようとした場合のエラーコード
     * status: 400
     * message: "社員は既に有効化されています。"
     * </pre>
     */
    public static final String ALREADY_ACTIVE = BAD_REQUEST + "1003";

    /**
     * <pre>
     * 存在しない社員を取得しようとした場合のエラーコード
     * status: 404
     * message: "社員情報が見つかりませんでした。"
     * </pre>
     */
    public static final String NOT_EXISTS = NOT_FOUND + "1001";

}
