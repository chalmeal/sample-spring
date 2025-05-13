package sample.repository.dao;

import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import sample.model.Product;
import sample.model.mapper.ProductMapper;

/**
 * <pre>
 * 商品のDao
 * </pre>
 */
@Repository
public class ProductDao extends DaoHelper {
    // DI
    // JdbcTenplate
    private final NamedParameterJdbcTemplate jdbcTemplate;
    // Mapper
    private final ProductMapper mapper;

    // 初期化
    public ProductDao(DataSource dataSource, ProductMapper mapper) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.mapper = mapper;
    }

    // 商品取得SQL
    private static final String SQL_SELECT_PRODUCT = " SELECT "
            + "'SQL_SELECT_PRODUCT' AS sql_type, "
            + "product_id, "
            + "product_name, "
            + "category_id, "
            + "client_id, "
            + "amount, "
            + "status "
            + "FROM products ";

    /**
     * <pre>
     * 商品IDから商品を取得
     * </pre>
     * 
     * @param productId
     * @return 商品
     */
    public Optional<Product> getProductById(String productId) {
        try {
            StringBuilder sql = new StringBuilder()
                    .append(SQL_SELECT_PRODUCT)
                    .append(SQL_WHERE).append(" product_id = :productId ");
            MapSqlParameterSource param = new MapSqlParameterSource()
                    .addValue("productId", productId);

            Product product = jdbcTemplate.queryForObject(sql.toString(), param, mapper);

            return Optional.ofNullable(product);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
