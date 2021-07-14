CREATE TABLE statistic
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    event_id       BIGINT,
    order_count    BIGINT,
    favorite_count BIGINT,
    viewed_count   BIGINT,
    FOREIGN KEY (event_id) REFERENCES event (id)
);

alter table `event`
    add `statistic_id` BIGINT;

ALTER TABLE event
    ADD CONSTRAINT fk_statistic_id FOREIGN KEY (statistic_id) REFERENCES statistic (id)