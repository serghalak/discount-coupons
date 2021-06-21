package com.exadel.sandbox.service;

import com.exadel.sandbox.dto.response.city.CityResponse;
import com.exadel.sandbox.model.location.City;

import java.util.List;
import java.util.Set;

public interface CityService {

    List<City> findAll();

    List<City> findCitiesByCountryNameOrderByName(String countryName);

    Set<CityResponse> findCitiesByEventStatusActive();

    Set<CityResponse> findCitiesByFavoriteEvent(Long userId);

    //Set<CityDto> findCityByUserId(Long userId);
}
