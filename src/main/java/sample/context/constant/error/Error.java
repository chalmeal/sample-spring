package sample.context.constant.error;

/**
 * <p>
 * エラーコード定数クラス
 * </p>
 */
public class Error {

    /**
     * <p>
     * 社員のベースエラーコード
     * </p>
     */
    public static final String ERROR_CODE_EMPLOYEE = "E10";

    /**
     * <p>
     * APIリソースが見つからない場合のエラーコード
     * message: "対象のAPIリソースが見つかりませんでした。"
     * </p>
     */
    public static final String NOT_FOUND = "E99-0001";

    /**
     * <p>
     * サーバー側のエラーコード
     * message: "サーバー側で予期せぬエラーが発生しました。"
     * </p>
     */
    public static final String INTERNAL_SERVER_ERROR = "E99-9999";

}
