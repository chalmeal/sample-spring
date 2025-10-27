package sample.repository.impl;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import sample.dto.request.log.LogWriteDto;
import sample.repository.LogRepository;
import sample.repository.impl.dao.LogDao;

/**
 * <pre>
 * ログのRepositoryクラス
 * </pre>
 */
@Repository
public class LogRepositoryImpl extends LogDao implements LogRepository {
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
        SqlParamSource source = this.startWrite(param, log);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(source.getSql(), source.getParam(), keyHolder, new String[] { "log_id" });

        return keyHolder.getKey().intValue();
    }

    /**
     * {@inheritDoc}
     */
    public void endWriteLog(LogWriteDto log) throws RuntimeException {
        MapSqlParameterSource param = new MapSqlParameterSource();
        SqlParamSource source = this.endWrite(param, log);

        jdbcTemplate.update(source.getSql(), source.getParam());
    }

}
