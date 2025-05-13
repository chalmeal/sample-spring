package sample.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import sample.model.type.ProductStatusType;

/**
 * <pre>
 * 商品を定義します。
 * </pre>
 */
@Getter
@Setter
@Accessors(chain = true)
public class Product {
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

    /** 登録日時 */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdAt;

    /** 更新日時 */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime updatedAt;
}
