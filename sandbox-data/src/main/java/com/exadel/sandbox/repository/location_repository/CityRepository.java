package com.exadel.sandbox.repository.location_repository;

import com.exadel.sandbox.model.location.City;
import com.exadel.sandbox.model.vendorinfo.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findCitiesByCountryNameOrderByName(String country);

    @Query("SELECT DISTINCT l.city FROM Event e " +
            "JOIN e.locations l " +
            "WHERE e.status = ?1")
    Set<City> findCitiesByEventStatus(Status status);

    @Query("SELECT DISTINCT loc.city FROM User u " +
            "JOIN u.savedEvents sav_ev " +
            "JOIN sav_ev.locations loc " +
            "WHERE u.id = ?1")
    Set<City> findCitiesByFavoriteEvents(@Param("userId") Long id);

    @Query("SELECT loc.city FROM User u " +
            "JOIN u.location loc " +
            "WHERE u.id = ?1")
    City findCityByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT name\n" +
            "FROM city\n" +
            "         LEFT JOIN location l ON city.id = l.city_id\n" +
            "         LEFT JOIN user u ON l.id = u.location_id\n" +
            "WHERE u.id = :userId",
            nativeQuery = true)
    Optional<String> findCityNameByUserId(Long userId);

}