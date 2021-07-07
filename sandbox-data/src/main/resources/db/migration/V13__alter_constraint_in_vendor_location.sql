ALTER TABLE vendor_location
    DROP FOREIGN KEY vendor_location_ibfk_1;
ALTER TABLE vendor_location
    DROP FOREIGN KEY vendor_location_ibfk_2;


ALTER TABLE vendor_location
    ADD CONSTRAINT fk_vendor_location_vendor_id FOREIGN KEY (vendor_id) REFERENCES vendor (id)
        ON DELETE CASCADE;

ALTER TABLE vendor_location
    ADD CONSTRAINT fk_vendor_location_location_id FOREIGN KEY (location_id) REFERENCES location (id)
        ON DELETE CASCADE;