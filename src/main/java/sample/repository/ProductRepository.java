package sample.repository;

import java.util.Optional;

import sample.model.Product;

/**
 * <pre>
 * 商品のRepositoryインターフェース
 * 
 * <pre>
 */
public interface ProductRepository {

    /**
     * <pre>
     * 商品IDから商品を取得
     * </pre>
     * 
     * @param productId 商品ID
     * @return 商品
     */
    Optional<Product> getProduct(String productId);

}
