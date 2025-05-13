package sample.service;

import sample.dto.response.ProductResponseDto;

/**
 * <pre>
 * 商品のServiceインターフェース
 * </pre>
 */
public interface ProductService {

    /**
     * <pre>
     * 商品IDから商品を取得
     * 一致する商品が存在しない場合はエラーを返却
     * </pre>
     * 
     * @param productId 商品ID
     * @return 商品
     */
    ProductResponseDto getProduct(String productId);

}
