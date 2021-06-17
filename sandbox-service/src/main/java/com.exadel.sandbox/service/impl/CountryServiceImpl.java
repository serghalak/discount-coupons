package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.CountryDto;
import com.exadel.sandbox.model.location.Country;
import com.exadel.sandbox.repository.location_repository.CityRepository;
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

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final ModelMapper mapper;

    @Autowired
    public CountryServiceImpl(CityRepository cityRepository, CountryRepository countryRepository, ModelMapper mapper) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll(Sort.by("name"));
    }

    @Override
    public CountryDto getCountryById(Long id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException();
        }
        final var country = countryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("State with id " + id + " not found"));
        return mapper.map(country, CountryDto.class);
    }

    @Override
    public Country create(CountryDto countryDto) {
        if (countryDto == null) {
            throw new IllegalArgumentException();
        }
        return countryRepository.save(
                mapper.map(countryDto, Country.class)
        );
    }

    @Override
    public Country update(Long id, CountryDto countryDto) {
        if (countryDto == null || id == null) {
            throw new IllegalArgumentException();
        }
        final Country build = mapper.map(countryDto, Country.class);
        build.setId(id);
        return countryRepository.save(build);
    }

    @Override
    public void delete(Long id) {
        countryRepository.deleteById(id);
    }

}
