ALTER TABLE user
    DROP
        FOREIGN KEY fk_user_role_id;

DROP TABLE IF EXISTS role;

ALTER TABLE user
    ADD role ENUM ('USER', 'MODERATOR', 'ADMIN');

ALTER TABLE event
    DROP
        FOREIGN KEY fk_event_status_id;

DROP TABLE IF EXISTS status;

ALTER TABLE event
    ADD status ENUM ('NEW', 'ACTIVE', 'EXPIRED');