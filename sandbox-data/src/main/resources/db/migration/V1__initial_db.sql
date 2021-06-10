CREATE TABLE category
(
    id          BIGINT NOT NULL AUTO_INCREMENT,
    description VARCHAR(255),
    name        VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE city
(
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    name       VARCHAR(255) NOT NULL,
    country_id BIGINT       NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE country
(
    id   BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE event
(
    id           BIGINT NOT NULL AUTO_INCREMENT,
    date_begin   DATE,
    date_end     DATE,
    description  VARCHAR(255),
    email        VARCHAR(255),
    is_online    BIT    NOT NULL,
    evaluate     INTEGER,
    limitation   INTEGER,
    name         VARCHAR(255),
    phone_number VARCHAR(255),
    price        DECIMAL(19, 2),
    total_count  INTEGER,
    status_id    BIGINT,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE event_location
(
    event_id    BIGINT NOT NULL,
    location_id BIGINT NOT NULL,
    PRIMARY KEY (event_id, location_id)
) ENGINE = InnoDB;

CREATE TABLE event_product
(
    product_id BIGINT NOT NULL,
    event_id   BIGINT NOT NULL,
    PRIMARY KEY (event_id, product_id)
) ENGINE = InnoDB;

CREATE TABLE feedback
(
    event_id BIGINT NOT NULL,
    user_id  BIGINT NOT NULL,
    PRIMARY KEY (event_id, user_id)
) ENGINE = InnoDB;

CREATE TABLE location
(
    id        BIGINT NOT NULL AUTO_INCREMENT,
    latitude  DOUBLE PRECISION,
    longitude DOUBLE PRECISION,
    number    VARCHAR(255),
    street    VARCHAR(255),
    city_id   BIGINT,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE product
(
    id          BIGINT NOT NULL AUTO_INCREMENT,
    description VARCHAR(255),
    link        VARCHAR(255),
    name        VARCHAR(255),
    category_id BIGINT,
    vendor_id   BIGINT,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE role
(
    id   BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE saved_event
(
    event_id BIGINT NOT NULL,
    user_id  BIGINT NOT NULL,
    PRIMARY KEY (user_id, event_id)
) ENGINE = InnoDB;

CREATE TABLE status
(
    id   BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE user
(
    id          BIGINT NOT NULL AUTO_INCREMENT,
    email       VARCHAR(255),
    first_name  VARCHAR(255),
    enable      BIT,
    last_name   VARCHAR(255),
    password    VARCHAR(255),
    username    VARCHAR(255),
    location_id BIGINT,
    role_id     BIGINT,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE user_order
(
    event_id BIGINT NOT NULL,
    user_id  BIGINT NOT NULL,
    PRIMARY KEY (user_id, event_id)
) ENGINE = InnoDB;

CREATE TABLE vendor
(
    id           BIGINT NOT NULL AUTO_INCREMENT,
    description  VARCHAR(255),
    email        VARCHAR(255),
    name         VARCHAR(255),
    phone_number VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE = InnoDB;

ALTER TABLE saved_event
    ADD CONSTRAINT uk_event_id UNIQUE (event_id);
ALTER TABLE user
    ADD CONSTRAINT uk_email UNIQUE (email);
ALTER TABLE user
    ADD CONSTRAINT uk_username UNIQUE (username);
ALTER TABLE city
    ADD CONSTRAINT fk_city_country_id FOREIGN KEY (country_id) REFERENCES country (id);
ALTER TABLE event
    ADD CONSTRAINT fk_event_status_id FOREIGN KEY (status_id) REFERENCES status (id);
ALTER TABLE event_location
    ADD CONSTRAINT fk_event_location_location_id FOREIGN KEY (location_id) REFERENCES location (id);
ALTER TABLE event_location
    ADD CONSTRAINT fk_event_location_event_id FOREIGN KEY (event_id) REFERENCES event (id);
ALTER TABLE event_product
    ADD CONSTRAINT fk_event_product_event_id FOREIGN KEY (event_id) REFERENCES event (id);
ALTER TABLE event_product
    ADD CONSTRAINT fk_event_product_product_id FOREIGN KEY (product_id) REFERENCES product (id);
ALTER TABLE feedback
    ADD CONSTRAINT fk_feedback_user_id FOREIGN KEY (user_id) REFERENCES user (id);
ALTER TABLE feedback
    ADD CONSTRAINT fk_feedback_event_id FOREIGN KEY (event_id) REFERENCES event (id);
ALTER TABLE location
    ADD CONSTRAINT fk_location_city_id FOREIGN KEY (city_id) REFERENCES city (id);
ALTER TABLE product
    ADD CONSTRAINT fk_product_category_id FOREIGN KEY (category_id) REFERENCES category (id);
ALTER TABLE product
    ADD CONSTRAINT fk_product_vendor_id FOREIGN KEY (vendor_id) REFERENCES vendor (id);
ALTER TABLE saved_event
    ADD CONSTRAINT fk_saved_event_user_id FOREIGN KEY (user_id) REFERENCES user (id);
ALTER TABLE saved_event
    ADD CONSTRAINT fk_saved_event_event_id FOREIGN KEY (event_id) REFERENCES event (id);
ALTER TABLE user
    ADD CONSTRAINT fk_user_location_id FOREIGN KEY (location_id) REFERENCES location (id);
ALTER TABLE user
    ADD CONSTRAINT fk_user_role_id FOREIGN KEY (role_id) REFERENCES role (id);
ALTER TABLE user_order
    ADD CONSTRAINT fk_user_order_user_id FOREIGN KEY (user_id) REFERENCES user (id);
ALTER TABLE user_order
    ADD CONSTRAINT fk_user_order_event_id FOREIGN KEY (event_id) REFERENCES event (id);