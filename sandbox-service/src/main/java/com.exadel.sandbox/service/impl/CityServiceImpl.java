package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.CityDto;
import com.exadel.sandbox.model.location.City;
import com.exadel.sandbox.model.vendorinfo.Status;
import com.exadel.sandbox.repository.location_repository.CityRepository;
import com.exadel.sandbox.service.CityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final ModelMapper mapper;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, ModelMapper mapper) {
        this.cityRepository = cityRepository;
        this.mapper = mapper;
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll(Sort.by("name"));
    }

    @Override
    public List<City> findCitiesByCountryNameOrderByName(String countryName) {
        return cityRepository.findCitiesByCountryNameOrderByName(countryName);
    }

    @Override
    public Set<CityDto> findCitiesByEventStatusActive() {
        return cityRepository.findCitiesByEventStatus(Status.NEW)
                .stream()
                .map(city -> mapper.map(city, CityDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<CityDto> findCitiesByFavoriteEvent(Long userId) {
        return cityRepository.findCitiesByFavoriteEvents(userId)
                .stream()
                .map(city -> mapper.map(city, CityDto.class))
                .collect(Collectors.toSet());
    }
}
