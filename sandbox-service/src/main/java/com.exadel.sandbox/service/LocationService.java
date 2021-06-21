package com.exadel.sandbox.service;

import com.exadel.sandbox.dto.request.location.LocationRequest;
import com.exadel.sandbox.dto.response.location.LocationResponse;
import com.exadel.sandbox.model.location.City;
import com.exadel.sandbox.model.location.Location;

import java.util.List;

public interface LocationService {

    List<Location> findAll();

    List<Location> findByCity(City city);

    List<Location> getAllLocationByCity(String cityName);

    LocationResponse create(LocationRequest locationRequest);

    LocationResponse update(Long locationId, LocationRequest locationRequest);

}
