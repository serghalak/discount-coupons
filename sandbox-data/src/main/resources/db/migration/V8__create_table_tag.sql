CREATE TABLE tag
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100),
    category_id BIGINT,
    FOREIGN KEY (category_id) REFERENCES category (id)
);

INSERT INTO tag (id, name, category_id)
VALUES (1, 'shoes', 5),
       (2, 'running', 5);