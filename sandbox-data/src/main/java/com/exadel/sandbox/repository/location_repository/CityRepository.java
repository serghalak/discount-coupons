package com.exadel.sandbox.repository.location_repository;

import com.exadel.sandbox.model.location.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findCitiesByCountryNameOrderByName(String country);
}
