package sample.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import sample.dto.ResponseDto;
import sample.model.type.ProductStatusType;

/**
 * <pre>
 * 商品情報を保持するDto
 * </pre>
 */
@Getter
@Setter
@Accessors(chain = true)
public class ProductResponseDto extends ResponseDto {
    /** 商品ID */
    private String productId;

    /** 商品名 */
    private String productName;

    /** カテゴリID */
    private String categoryId;

    /** 取引先ID */
    private String clientId;

    /** 単価 */
    private int amount;

    /** 状態 */
    private ProductStatusType status;
}
