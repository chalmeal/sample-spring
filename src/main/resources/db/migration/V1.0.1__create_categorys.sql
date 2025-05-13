CREATE TABLE categorys(
    category_id        VARCHAR(4)   NOT NULL,
    category_name      VARCHAR(20)  NOT NULL,
    status             VARCHAR(8)   NOT NULL,
    created_at         TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    updated_at         TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (category_id)
);