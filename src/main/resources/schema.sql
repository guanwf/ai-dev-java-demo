DROP TABLE IF EXISTS product;

CREATE TABLE product (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(128) NOT NULL,
    code        VARCHAR(64)  NOT NULL,
    category    VARCHAR(64),
    price       DECIMAL(12, 2),
    stock       INT DEFAULT 0,
    unit        VARCHAR(16),
    status      TINYINT DEFAULT 1,
    description VARCHAR(512),
    create_time DATETIME,
    update_time DATETIME
);
