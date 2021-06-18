package com.exadel.sandbox.controllers.location_controller;

import com.exadel.sandbox.dto.request.country.CountryRequest;
import com.exadel.sandbox.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/location")
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping(produces = {"application/json"}, path = "/allCountry")
    public ResponseEntity<?> getAllCountry() {
        return new ResponseEntity<>(countryService.findAll(), HttpStatus.OK);
    }

    @GetMapping(produces = {"application/json"}, path = "/country/{id}")
    public ResponseEntity<?> getCountryById(@PathVariable Long id) {
        final var countryById = countryService.getCountryById(id);

        return new ResponseEntity<>(countryById, HttpStatus.OK);
    }

    @PostMapping(produces = {"application/json"},
            consumes = {"application/json"},
            path = "/newCountry")
    public ResponseEntity<?> createCountry(@RequestBody final CountryRequest  countryRequest) {
        final var country = countryService.create(countryRequest);

        return ResponseEntity.ok(country);
    }

    @PutMapping(produces = {"application/json"},
            consumes = {"application/json"},
            path = "/updateCountry/{countryId}")
    public ResponseEntity<?> updateCountry(@PathVariable("countryId") long countryId,
                                           @RequestBody final CountryRequest countryRequest) {
        final var newCountry = countryService.update(countryId, countryRequest);

        return ResponseEntity.ok(newCountry);
    }

    @GetMapping("/deleteCountry/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        countryService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}