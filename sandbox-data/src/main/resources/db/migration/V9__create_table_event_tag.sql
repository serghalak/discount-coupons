CREATE TABLE event_tag
(
    tag_id   BIGINT,
    event_id BIGINT,
    FOREIGN KEY (tag_id) REFERENCES tag (id),
    FOREIGN KEY (event_id) REFERENCES event (id)
);

INSERT INTO event_tag(tag_id, event_id)
VALUES (1, 1),
       (2, 1);