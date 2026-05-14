CREATE TABLE notice (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    notice_type VARCHAR(50),
    title VARCHAR(255),
    created_at DATETIME,
    updated_at DATETIME
);