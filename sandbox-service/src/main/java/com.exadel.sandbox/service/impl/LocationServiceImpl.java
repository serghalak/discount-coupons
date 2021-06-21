package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.LocationDto;
import com.exadel.sandbox.dto.request.location.LocationRequest;
import com.exadel.sandbox.dto.response.location.LocationResponse;
import com.exadel.sandbox.model.location.City;
import com.exadel.sandbox.model.location.Location;
import com.exadel.sandbox.repository.location_repository.LocationRepository;
import com.exadel.sandbox.service.LocationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final ModelMapper mapper;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository, ModelMapper mapper) {
        this.locationRepository = locationRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public List<Location> findByCity(City city) {
        if (city == null) {
            throw new IllegalArgumentException();
        }

        return locationRepository.findByCity(city);
    }

    @Override
    public List<Location> getAllLocationByCity(String cityName) {
        if (cityName == null) {
            throw new IllegalArgumentException();
        }

        return locationRepository.getLocationsByCityName(cityName);
    }

    @Override
    public LocationResponse create(LocationRequest locationRequest) {
        if (locationRequest == null) {
            throw new IllegalArgumentException();
        }
        var location = mapper.map(
                locationRepository.save(mapper.map(locationRequest, Location.class)),
                LocationDto.class
        );

        return mapper.map(location, LocationResponse.class);
    }

    @Override
    public LocationResponse update(Long locationId, LocationRequest locationRequest) {
        if (locationRequest == null || locationId == null) {
            throw new IllegalArgumentException();
        }

        final var updatedLocation = mapper.map(locationRequest, Location.class);

        updatedLocation.setId(locationId);

        var location = mapper.map(locationRepository.save(updatedLocation), LocationResponse.class);

        return mapper.map(location, LocationResponse.class);
    }
}
