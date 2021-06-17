package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.LocationDto;
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
    public LocationDto create(LocationDto locationDto) {
        if (locationDto == null) {
            throw new IllegalArgumentException();
        }
        return mapper.map(
                locationRepository.save(mapper.map(locationDto, Location.class)),
                LocationDto.class
        );
//        return locationRepository.save(
////                Location.builder()
////                        .latitude(locationDto.getLatitude())
////                        .longitude(locationDto.getLongitude())
////                        .city(locationDto.getCity())
////                        .street(locationDto.getStreet())
////                        .number(locationDto.getNumber())
////                        .build()
//        );
    }

    @Override
    public LocationDto update(Long locationId, LocationDto locationDto) {
        if (locationDto == null || locationId == null) {
            throw new IllegalArgumentException();
        }
        final var updatedLocation = mapper.map(locationDto, Location.class);
//        final Location updatedLocation = Location.builder()
//                .latitude(locationDto.getLatitude())
//                .longitude(locationDto.getLongitude())
//                .city(locationDto.getCity())
//                .street(locationDto.getStreet())
//                .number(locationDto.getNumber())
//                .build();
        updatedLocation.setId(locationId);
        return mapper.map(locationRepository.save(updatedLocation), LocationDto.class);
    }
}
