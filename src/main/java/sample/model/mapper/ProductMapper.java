package sample.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import sample.model.Product;
import sample.model.type.ProductStatusType;

/**
 * <pre>
 * 商品のMapperクラス
 * </pre>
 */
@Component
public class ProductMapper implements RowMapper<Product> {
    private final BeanPropertyRowMapper<Product> mapper;

    // 初期化
    public ProductMapper() {
        this.mapper = new BeanPropertyRowMapper<>(Product.class);
    }

    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        String sqlType = rs.getString("sql_type");

        switch (sqlType) {
            case "SQL_SELECT_PRODUCT":
                return getProduct(rs, rowNum);
            default:
                return new Product();
        }
    }

    /**
     * <pre>
     * 商品取得SQLマッピング
     * </pre>
     * 
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    private Product getProduct(ResultSet rs, int rowNum) throws SQLException {
        Product model = mapper.mapRow(rs, rowNum);
        model.setProductId(rs.getString("product_id"));
        model.setProductName(rs.getString("product_name"));
        model.setCategoryId(rs.getString("category_id"));
        model.setClientId(rs.getString("client_id"));
        model.setAmount(rs.getInt("amount"));
        if (rs.getString("status") == ProductStatusType.VALID.toString()) {
            model.setStatus(ProductStatusType.VALID);
        } else {
            model.setStatus(ProductStatusType.INVALID);
        }

        return model;
    }

}
