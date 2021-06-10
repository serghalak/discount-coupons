ALTER TABLE user_order
    ADD COLUMN order_count INTEGER;

ALTER TABLE feedback
    ADD COLUMN evaluate INTEGER;

ALTER TABLE event
    ADD COLUMN discount INTEGER