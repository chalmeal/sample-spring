package sample.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sample.constant.ResponseConst;
import sample.dto.response.ProductResponseDto;
import sample.exception.ServiceException;
import sample.model.Product;
import sample.repository.ProductRepository;
import sample.service.ProductService;

/**
 * <pre>
 * 商品のService実装クラス
 * </pre>
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    // DI
    private final ProductRepository repository;

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductResponseDto getProduct(String productId) {
        ProductResponseDto result = new ProductResponseDto();

        Optional<Product> optProduct = repository.getProduct(productId);
        optProduct.orElseThrow(() -> {
            return new ServiceException(ResponseConst.Status.NOT_FOUND.getStatus(),
                    ResponseConst.Error.PRODUCT_GET_NOT_FOUND);
        });

        Product product = optProduct.get();
        result.setProductId(product.getProductId());
        result.setProductName(product.getProductName());
        result.setCategoryId(product.getCategoryId());
        result.setClientId(product.getClientId());
        result.setAmount(product.getAmount());
        result.setStatus(product.getStatus());

        return result;
    }

}
