CREATE TABLE products(
    product_id         VARCHAR(8)   NOT NULL,
    product_name       VARCHAR(50)  NOT NULL,
    category_id        VARCHAR(8)   NOT NULL,
    client_id          VARCHAR(8)   NOT NULL,
    amount             BIGINT       NOT NULL,
    status             VARCHAR(8)   NOT NULL,
    created_at         TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    updated_at         TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (product_id)
);