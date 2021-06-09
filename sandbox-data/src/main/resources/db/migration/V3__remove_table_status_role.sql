DROP TABLE if EXISTS status;

DROP TABLE if EXISTS 'role';

ALTER TABLE 'user'
DROP
COLUMN role_id;

ALTER TABLE 'user'
    ADD COLUMN 'role' varchar (255);

ALTER TABLE event
DROP
COLUMN status_id;

ALTER TABLE event
    ADD COLUMN status varchar(255);