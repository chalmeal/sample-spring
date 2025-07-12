package sample.context.constant.error;

/**
 * <pre>
 * エラーコード定数クラス
 * </pre>
 */
public class Error {

    /**
     * <pre>
     * 社員のベースエラーコード
     * </pre>
     */
    public static final String ERROR_CODE_EMPLOYEE = "E10";

    /**
     * <pre>
     * APIリソースが見つからない場合のエラーコード
     * message: "対象のAPIリソースが見つかりませんでした。"
     * </pre>
     */
    public static final String NOT_FOUND = "E99-0001";

    /**
     * <pre>
     * サーバー側のエラーコード
     * message: "サーバー側で予期せぬエラーが発生しました。"
     * </pre>
     */
    public static final String INTERNAL_SERVER_ERROR = "E99-9999";

}
