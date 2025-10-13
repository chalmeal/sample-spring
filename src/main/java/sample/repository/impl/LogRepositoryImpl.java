package sample.repository.impl;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import sample.dto.request.log.LogWriteDto;
import sample.repository.LogRepository;
import sample.repository.sql.LogSql;

/**
 * <pre>
 * ログのRepositoryクラス
 * </pre>
 */
@Repository
public class LogRepositoryImpl extends LogSql implements LogRepository {
    // DI
    // JdbcTemplate
    private final NamedParameterJdbcTemplate jdbcTemplate;

    /**
     * {@inheritDoc}
     */
    public LogRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * {@inheritDoc}
     */
    public long startWriteLog(LogWriteDto log) throws RuntimeException {
        MapSqlParameterSource param = new MapSqlParameterSource();

        String sql = SQL_START_WRITE_LOG;
        param.addValue("executorId", log.getExecutorId());
        param.addValue("processName", log.getProcessName());
        param.addValue("logType", log.getLogType());
        param.addValue("category", log.getCategory());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, param, keyHolder, new String[] { "log_id" });

        return keyHolder.getKey().intValue();
    }

    /**
     * {@inheritDoc}
     */
    public void endWriteLog(LogWriteDto log) throws RuntimeException {
        MapSqlParameterSource param = new MapSqlParameterSource();

        String sql = SQL_END_WRITE_LOG;
        param.addValue("logType", log.getLogType());
        param.addValue("message", log.getMessage());
        param.addValue("executedAt", log.getExecutedAt());
        param.addValue("logId", log.getLogId());

        jdbcTemplate.update(sql, param);
    }

}
