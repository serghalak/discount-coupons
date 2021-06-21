package com.exadel.sandbox.service;

import com.exadel.sandbox.dto.request.country.CountryRequest;
import com.exadel.sandbox.dto.response.country.CountryResponse;
import com.exadel.sandbox.model.location.Country;

import java.util.List;

public interface CountryService {

    List<Country> findAll();

    CountryResponse getCountryById(Long id);

    CountryResponse create(CountryRequest countryRequest);

    CountryResponse update(Long id, CountryRequest countryRequest);

    void delete(Long id);

}
