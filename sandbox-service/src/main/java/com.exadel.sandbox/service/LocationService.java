package com.exadel.sandbox.service;

import com.exadel.sandbox.dto.LocationDto;
import com.exadel.sandbox.model.location.City;
import com.exadel.sandbox.model.location.Location;

import java.util.List;

public interface LocationService {

    List<Location> findAll();

    List<Location> findByCity(City city);

    List<Location> getAllLocationByCity(String cityName);

    LocationDto create(LocationDto locationDto);

    LocationDto update(Long locationId, LocationDto locationDto);

}
