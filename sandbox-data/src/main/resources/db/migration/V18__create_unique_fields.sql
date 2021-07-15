ALTER TABLE subscription
    ADD UNIQUE INDEX_type_name_user (subscriber_type, subscriber_id, user_id);