package sample.context.constant.error;

/**
 * <pre>
 * エラーコード定数クラス
 * </pre>
 */
public class Error {

    /**
     * <pre>
     * [400]リクエストが不正な場合のエラーコード
     * </pre>
     */
    public static final String BAD_REQUEST = "E400-";
    /** message: "リクエストが不正です。" */
    public static final String BAD_REQUEST_GLOBAL = BAD_REQUEST + "0000";

    /**
     * <pre>
     * [401]認証に失敗した場合のエラーコード
     * </pre>
     */
    public static final String UNAUTHORIZED = "E401-";
    /** message: "認証に失敗しました。" */
    public static final String UNAUTHORIZED_GLOBAL = UNAUTHORIZED + "0000";

    /**
     * <pre>
     * [403]認可に失敗した場合のエラーコード
     * </pre>
     */
    public static final String FORBIDDEN = "E403-";
    /** message: "認可に失敗しました。" */
    public static final String FORBIDDEN_GLOBAL = FORBIDDEN + "0000";

    /**
     * <pre>
     * [404]リソースが見つからない場合のエラーコード
     * </pre>
     */
    public static final String NOT_FOUND = "E404-";
    /** message: "リソースが見つかりませんでした。" */
    public static final String NOT_FOUND_GLOBAL = NOT_FOUND + "0000";

    /**
     * <pre>
     * [405]リクエストメソッドが不正な場合のエラーコード
     * </pre>
     */
    public static final String METHOD_NOT_ALLOWED = "E405-";
    /** message: "リクエストメソッドが不正です。" */
    public static final String METHOD_NOT_ALLOWED_GLOBAL = METHOD_NOT_ALLOWED + "0000";

    /**
     * <pre>
     * [500]サーバー側のエラーコード
     * </pre>
     */
    public static final String INTERNAL_SERVER_ERROR = "E500-";
    /** message: "サーバー側でエラーが発生しました。" */
    public static final String INTERNAL_SERVER_ERROR_GLOBAL = INTERNAL_SERVER_ERROR + "0000";

}
