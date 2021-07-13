package com.exadel.sandbox.repository.location_repository;

import com.exadel.sandbox.model.location.City;
import com.exadel.sandbox.model.location.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long>, LocationRepositoryCustom {

    List<Location> findAll();

    List<Location> findByCity(City city);

    List<Location> getLocationsByCityName(String cityName);

    @Query("select distinct l from Location l " +
            "where (l.id in (?1))")
    Set<Location> getLocationsById(Set<Long> locationsIds);


}
