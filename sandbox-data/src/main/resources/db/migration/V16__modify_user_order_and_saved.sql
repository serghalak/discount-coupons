ALTER TABLE user_order DROP date_event;

ALTER TABLE user_order
    ADD date_event TIMESTAMP;

ALTER TABLE user_order
    DROP FOREIGN KEY `FK_user_order_event_id`,
    DROP FOREIGN KEY `FK_user_order_user_id`;

ALTER TABLE user_order DROP PRIMARY KEY;


ALTER TABLE user_order
    ADD id bigint  not null auto_increment
    primary key first,
    ADD CONSTRAINT FK_user_order_event_id FOREIGN KEY (event_id)
        REFERENCES event (id),
        ADD CONSTRAINT FK_user_order_user_id FOREIGN KEY (user_id)
        REFERENCES user (id);

ALTER TABLE saved_event
    ADD saved_date TIMESTAMP;