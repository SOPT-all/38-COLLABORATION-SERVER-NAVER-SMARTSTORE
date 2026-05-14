CREATE TABLE category (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name ENUM(
      'FURNITURE_INTERIOR',
      'BOOK',
      'DIGITAL_APPLIANCE',
      'HEALTH',
      'SPORTS_LEISURE',
      'FOOD',
      'LEISURE_CONVENIENCE',
      'BABY_PARENTING',
      'FASHION_CLOTHES',
      'FASHION_ACCESSORIES',
      'COSMETIC_BEAUTY'
    ) NOT NULL,
    created_at DATETIME,
    updated_at DATETIME
);

CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price INT NOT NULL,
    category_id BIGINT NOT NULL,
    created_at DATETIME,
    updated_at DATETIME,

    CONSTRAINT fk_product_category
        FOREIGN KEY (category_id)
        REFERENCES category(id)
);

CREATE TABLE image (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    image_url VARCHAR(255) NOT NULL,
    content_type VARCHAR(255) NOT NULL,
    image_order INT NOT NULL,
    is_representative BOOLEAN NOT NULL,
    product_id BIGINT NOT NULL,
    created_at DATETIME,
    updated_at DATETIME,

    CONSTRAINT fk_image_product
        FOREIGN KEY (product_id)
        REFERENCES product(id)
);