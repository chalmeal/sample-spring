package sample.repository.impl.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import lombok.Getter;

/**
 * <pre>
 * DAOヘルパークラス
 * </pre>
 */
public class DaoHelper {

    @Getter
    protected class SqlParamSource {
        private String sql;
        private MapSqlParameterSource param;

        public SqlParamSource(String sql, MapSqlParameterSource param) {
            this.sql = sql;
            this.param = param;
        }
    }

}