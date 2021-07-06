CREATE TABLE vendor_location
(
    vendor_id BIGINT,
    location_id BIGINT,
    primary key (vendor_id, location_id),
    FOREIGN KEY (vendor_id) REFERENCES vendor (id),
    FOREIGN KEY (location_id) REFERENCES location (id)
);

