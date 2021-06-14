package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.model.location.City;
import com.exadel.sandbox.repository.location_repository.CityRepository;
import com.exadel.sandbox.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll(Sort.by("name"));
    }

    @Override
    public List<City> findCitiesByCountryNameOrderByName(String countryName) {
        return cityRepository.findCitiesByCountryNameOrderByName(countryName);
    }
}
