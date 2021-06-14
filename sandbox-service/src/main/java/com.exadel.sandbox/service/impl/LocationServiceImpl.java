package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.request.LocationDto;
import com.exadel.sandbox.model.location.City;
import com.exadel.sandbox.model.location.Location;
import com.exadel.sandbox.repository.location_repository.LocationRepository;
import com.exadel.sandbox.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public List<Location> findByCity(City city) {
        return locationRepository.findByCity(city);
    }

    @Override
    public List<Location> getAllLocationByCity(String cityName) {
        return locationRepository.getLocationsByCityName(cityName);
    }

    @Override
    public Location create(LocationDto locationDto) {
        if (locationDto == null) {
            throw new IllegalArgumentException();
        }
        return locationRepository.save(
                Location.builder()
                        .latitude(locationDto.getLatitude())
                        .longitude(locationDto.getLongitude())
                        .city(locationDto.getCity())
                        .street(locationDto.getStreet())
                        .number(locationDto.getNumber())
                        .build()
        );
    }

    @Override
    public Location update(Long locationId, LocationDto locationDto) {
        if (locationDto == null || locationId == null) {
            throw new IllegalArgumentException();
        }
        final Location updatedLocation = Location.builder()
                .latitude(locationDto.getLatitude())
                .longitude(locationDto.getLongitude())
                .city(locationDto.getCity())
                .street(locationDto.getStreet())
                .number(locationDto.getNumber())
                .build();
        updatedLocation.setId(locationId);
        return locationRepository.save(updatedLocation);
    }
}
