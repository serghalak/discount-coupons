CREATE TABLE viewed_event
(
    event_id   BIGINT,
    user_id    BIGINT,
    date_event TIMESTAMP,
    PRIMARY KEY (event_id, user_id),
    FOREIGN KEY (event_id) REFERENCES event (id),
    FOREIGN KEY (user_id) REFERENCES user (id)
);