package sample.repository.impl.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import sample.dto.request.log.LogWriteDto;

/**
 * <pre>
 * ログのDAOクラス
 * </pre>
 */
public class LogDao extends DaoHelper {

    /**
     * <pre>
     * ログ開始書込みSQL
     * </pre>
     * 
     * @param param パラメータソース
     * @param log   ログ書込みDTO
     * @return
     */
    protected SqlParamSource startWrite(MapSqlParameterSource param, LogWriteDto log) {
        String sql = "INSERT INTO "
                + "logs (executor_id, process_name, log_type, category) "
                + "VALUES (:executorId, :processName, :logType, :category)";

        param.addValue("executorId", log.getExecutorId());
        param.addValue("processName", log.getProcessName());
        param.addValue("logType", log.getLogType());
        param.addValue("category", log.getCategory());

        return new SqlParamSource(sql.toString(), param);
    }

    /**
     * <pre>
     * ログ終了書込みSQL
     * </pre>
     * 
     * @param param パラメータソース
     * @param log   ログ書込みDTO
     * @return
     */
    protected SqlParamSource endWrite(MapSqlParameterSource param, LogWriteDto log) {
        String sql = "UPDATE "
                + "logs SET "
                + "log_type = :logType, "
                + "message = :message, "
                + "executed_at = :executedAt "
                + "WHERE log_id = :logId";

        param.addValue("logId", log.getLogId());
        param.addValue("logType", log.getLogType());
        param.addValue("message", log.getMessage());
        param.addValue("executedAt", log.getExecutedAt());

        return new SqlParamSource(sql, param);
    }
}
