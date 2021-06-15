package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.CountryDto;
import com.exadel.sandbox.model.location.Country;
import com.exadel.sandbox.repository.location_repository.CountryRepository;
import com.exadel.sandbox.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll(Sort.by("name"));
    }

    @Override
    public Country create(CountryDto countryDto) {
        if (countryDto == null) {
            throw new IllegalArgumentException();
        }
        return countryRepository.save(
                Country.builder()
                        .name(countryDto.getName())
                        .cities(countryDto.getCities())
                        .build()
        );
    }

    @Override
    public Country update(Long id, CountryDto countryDto) {
        if (countryDto == null || id == null) {
            throw new IllegalArgumentException();
        }
        final Country build = Country.builder()
                .name(countryDto.getName())
                .cities(countryDto.getCities())
                .build();
        build.setId(id);
        return countryRepository.save(build);
    }

    @Override
    public void delete(Long id) {
        countryRepository.deleteById(id);
    }
}
