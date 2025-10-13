package sample.repository.sql;

/**
 * <pre>
 * ログのSQLクラス
 * SQLのパラメタがnullableの場合は、repository内で動的SQLを組み立ててください。
 * </pre>
 */
public class LogSql {

    /**
     * <pre>
     * ログ書込みSQL（開始）
     * </pre>
     * 
     * @param executorId 実行者ID
     * @param logType    ログ種別
     * @param category   カテゴリ
     */
    protected final String SQL_START_WRITE_LOG = """
            INSERT INTO logs (executor_id, process_name, log_type, category)
            VALUES (:executorId, :processName, :logType, :category)
            """;

    /**
     * <pre>
     * ログ書込みSQL（終了）
     * </pre>
     * 
     * @param logType    ログ種別
     * @param message    メッセージ
     * @param executedAt 実行日時
     * @param logId      ログID
     */
    protected final String SQL_END_WRITE_LOG = """
            UPDATE logs
            SET log_type = :logType,
                message = :message,
                executed_at = :executedAt
            WHERE log_id = :logId
            """;
}
