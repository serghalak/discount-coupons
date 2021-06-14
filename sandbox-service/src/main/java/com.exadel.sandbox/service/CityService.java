package com.exadel.sandbox.service;

import com.exadel.sandbox.model.location.City;

import java.util.List;

public interface CityService {

    List<City> findAll();

    List<City> findCitiesByCountryNameOrderByName(String countryName);
}
