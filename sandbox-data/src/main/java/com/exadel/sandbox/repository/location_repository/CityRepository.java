package com.exadel.sandbox.repository.location_repository;

import com.exadel.sandbox.model.location.City;
import com.exadel.sandbox.model.vendorinfo.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findCitiesByCountryNameOrderByName(String country);

    @Query("SELECT l.city from Event e " +
            "join e.locations l " +
            "WHERE e.status = ?1")
    Set<City> findCitiesByEventStatus(Status status);

    @Query(value = "SELECT DISTINCT city.* " +
            "FROM city LEFT JOIN location l ON city.id = l.city_id " +
            "LEFT JOIN event_location el ON l.id = el.location_id " +
            "LEFT JOIN event e ON el.event_id = e.id " +
            "LEFT JOIN saved_event se ON e.id = se.event_id " +
            "WHERE se.user_id = ?2;", nativeQuery = true)
    Set<City> findCitiesByFavoriteEvents(Long id);

}
