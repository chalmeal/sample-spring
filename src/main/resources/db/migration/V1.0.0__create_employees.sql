-- 社員テーブル
CREATE TABLE employees(
    employee_id     BIGINT(4)   NOT NULL,
    employee_code   VARCHAR(8)  NOT NULL,
    name            VARCHAR(50)  NOT NULL,
    name_kana       VARCHAR(50)  NOT NULL,
    mail            VARCHAR(100) NOT NULL,
    department_code BIGINT(6)      NOT NULL,
    status          BIGINT(1)  NOT NULL,
    created_at      TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (employee_id)
);