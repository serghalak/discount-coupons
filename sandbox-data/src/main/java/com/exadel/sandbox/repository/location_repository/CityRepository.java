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

    @Query("select l.city from Location l " +
            "where l.id in (SELECT sav_ev.locations from User u " +
            "join u.savedEvents sav_ev " +
            "WHERE u.id = ?1)")
    Set<City> findCitiesByFavoriteEvents(Long id);

}
