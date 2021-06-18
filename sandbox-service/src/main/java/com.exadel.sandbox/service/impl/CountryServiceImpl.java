package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.request.country.CountryRequest;
import com.exadel.sandbox.dto.response.country.CountryResponse;
import com.exadel.sandbox.model.location.Country;
import com.exadel.sandbox.repository.location_repository.CountryRepository;
import com.exadel.sandbox.service.CountryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final ModelMapper mapper;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository, ModelMapper mapper) {
        this.countryRepository = countryRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll(Sort.by("name"));
    }

    @Override
    public CountryResponse getCountryById(Long id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException();
        }

        final var country = countryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("State with id " + id + " not found"));

        return mapper.map(country, CountryResponse.class);
    }

    @Override
    public CountryResponse create(CountryRequest countryRequest) {
        if (countryRequest == null) {
            throw new IllegalArgumentException();
        }

        var country = countryRepository.save(
                mapper.map(countryRequest, Country.class)
        );
        return mapper.map(country, CountryResponse.class);
    }

    @Override
    public CountryResponse update(Long id, CountryRequest countryRequest) {
        if (countryRequest == null || id == null) {
            throw new IllegalArgumentException();
        }

        final Country country = mapper.map(countryRequest, Country.class);
        country.setId(id);

        countryRepository.save(country);
        return mapper.map(country, CountryResponse.class);
    }

    @Override
    public void delete(Long id) {
        countryRepository.deleteById(id);
    }

}
