package sample.repository.impl;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import sample.model.Product;
import sample.repository.ProductRepository;
import sample.repository.dao.ProductDao;

/**
 * <pre>
 * 商品のRepository実装クラス
 * </pre>
 */
@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    // DI
    private final ProductDao dao;

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Product> getProduct(String productId) {
        return dao.getProductById(productId);
    }

}
