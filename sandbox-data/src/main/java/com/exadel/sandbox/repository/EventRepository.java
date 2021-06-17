package com.exadel.sandbox.repository;

import com.exadel.sandbox.model.location.Location;
import com.exadel.sandbox.model.vendorinfo.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Integer> {

    List<Event> findEventByLocations(Location location);
    
    Event findEventById(Long id);
    
    @Query(value = "SELECT * FROM event\n" +
            "    LEFT JOIN event_location ON event.id = event_location.event_id\n" +
            "    LEFT JOIN location ON event_location.location_id = location.id\n" +
            "    LEFT JOIN city ON location.city_id = city.id\n" +
            "    LEFT JOIN country ON city.country_id = country.id\n" +
            "    LEFT JOIN event_product ep ON event.id = ep.event_id\n" +
            "    LEFT JOIN product p ON ep.product_id = p.id\n" +
            "where vendor_id = :id", nativeQuery = true)
    List<Event> findAllByVendorId(Long id);

}
