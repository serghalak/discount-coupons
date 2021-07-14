CREATE TABLE subscription
(
    id                      BIGINT AUTO_INCREMENT PRIMARY KEY,
    subscriber_type         VARCHAR(30),
    subscriber_id           BIGINT,
    subscriber_name         VARCHAR(30),
    user_id                 BIGINT,
    FOREIGN KEY (user_id) REFERENCES user (id)
);