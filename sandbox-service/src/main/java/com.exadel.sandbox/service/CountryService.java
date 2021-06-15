package com.exadel.sandbox.service;

import com.exadel.sandbox.dto.CountryDto;
import com.exadel.sandbox.model.location.Country;

import java.util.List;

public interface CountryService {

    List<Country> findAll();

    Country create(CountryDto country);

    Country update(Long id, CountryDto countryDto);

    void delete(Long id);

}
