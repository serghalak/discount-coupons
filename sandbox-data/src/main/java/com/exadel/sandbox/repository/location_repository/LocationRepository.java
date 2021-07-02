package com.exadel.sandbox.repository.location_repository;

import com.exadel.sandbox.model.location.City;
import com.exadel.sandbox.model.location.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> ,LocationRepositoryCustom{

    List<Location> findAll();

    List<Location> findByCity(City city);

    List<Location> getLocationsByCityName(String cityName);

    @Query(value = "SELECT DISTINCT c.id, c.name, c.description FROM category c " +
            "JOIN event e on e.category_id=c.id " +
            "JOIN saved_event se on se.event_id=e.id " +
            "WHERE se.user_id = :userId", nativeQuery = true)
    List<Location> getAllEventsLocationsFromSaved(Long userId);

}
