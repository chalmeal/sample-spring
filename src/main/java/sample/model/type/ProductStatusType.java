package sample.model.type;

/**
 * <pre>
 * 商品の状態を表現します。
 * </pre>
 */
public enum ProductStatusType {
    VALID,
    INVALID;

    // 有効判定
    public Boolean isValid() {
        return this == VALID;
    }

    // 無効判定
    public Boolean isInvalid() {
        return this == INVALID;
    }
}
