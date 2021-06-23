package com.exadel.sandbox.controllers.location_controller;

import com.exadel.sandbox.dto.request.country.CountryRequest;
import com.exadel.sandbox.model.location.Country;
import com.exadel.sandbox.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/location")
public class CountryController {

    private final CountryService countryService;

    @GetMapping("/allCountry")
    public List<Country> getAllCountry() {
        return countryService.findAll();
    }

    @GetMapping("/country/{id}")
    public ResponseEntity<?> getCountryById(@PathVariable Long id) {

        final var countryById = countryService.getCountryById(id);

        return new ResponseEntity<>(countryById, HttpStatus.OK);
    }

    @GetMapping("/country")
    public ResponseEntity<?> getCountryByName(@RequestParam(name = "name", defaultValue = "") String name) {

        final var countryById = countryService.getCountryByName(name);

        return new ResponseEntity<>(countryById, HttpStatus.OK);
    }

    @PostMapping(produces = {"application/json"},
            consumes = {"application/json"},
            path = "/newCountry")
    public ResponseEntity<?> createCountry(@RequestBody final CountryRequest countryRequest) {

        final var country = countryService.create(countryRequest);

        return new ResponseEntity<>(country, HttpStatus.OK);
    }

    @PutMapping(produces = {"application/json"},
            consumes = {"application/json"},
            path = "/updateCountry/{countryId}")
    public ResponseEntity<?> updateCountry(@PathVariable("countryId") long countryId,
                                           @RequestBody final CountryRequest countryRequest) {

        final var newCountry = countryService.update(countryId, countryRequest);

        return new ResponseEntity<>(newCountry, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCountry/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        countryService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}