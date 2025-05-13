CREATE TABLE clients(
    client_id          VARCHAR(8)   NOT NULL,
    client_name        VARCHAR(50)  NOT NULL,
    client_name_kana   VARCHAR(50)  NOT NULL,
    pic_name           VARCHAR(50)  NOT NULL,
    pic_name_kana      VARCHAR(50)  NOT NULL,
    pic_tel_number     VARCHAR(20)  NOT NULL,
    pic_mail_address   VARCHAR(50)  NOT NULL,
    status             VARCHAR(8)   NOT NULL,
    created_at         TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    updated_at         TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (client_id)
);