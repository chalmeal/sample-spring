package sample.context.logger;

/**
 * <pre>
 * ログコンテキストクラス
 * ログに関する情報をスレッドローカルで保持します。
 * </pre>
 */
public class LoggerContext {
    private static final ThreadLocal<Long> context = new ThreadLocal<>();

    /**
     * <pre>
     * ログIDの設定
     * ログIDをスレッドローカルに設定します。
     * </pre>
     * 
     * @param logId
     */
    public static void setLogId(Long logId) {
        context.set(logId);
    }

    /**
     * <pre>
     * ログIDの取得
     * スレッドローカルからログIDを取得します。
     * </pre>
     * 
     * @return ログID
     */
    public static Long getLogId() {
        return context.get();
    }

    /**
     * <pre>
     * ログIDのクリア
     * スレッドローカルからログIDをクリアします。
     * </pre>
     */
    public static void clear() {
        context.remove();
    }
}