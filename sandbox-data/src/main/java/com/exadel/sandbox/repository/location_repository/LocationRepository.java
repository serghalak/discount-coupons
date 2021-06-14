package com.exadel.sandbox.repository.location_repository;

import com.exadel.sandbox.model.location.City;
import com.exadel.sandbox.model.location.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    List<Location> findAll();

    List<Location> findByCity(City city);

    List<Location> getLocationsByCityName(String cityName);
}
