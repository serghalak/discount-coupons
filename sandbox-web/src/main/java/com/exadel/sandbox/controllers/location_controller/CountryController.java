package com.exadel.sandbox.controllers.location_controller;

import com.exadel.sandbox.dto.CountryDto;
import com.exadel.sandbox.model.location.Country;
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

    @GetMapping("/getAllCountry")
    public ResponseEntity<?> getAllCountry() {
        return new ResponseEntity<>(countryService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/createCountry")
    public ResponseEntity<?> createCountry(@RequestBody final CountryDto countryDto) {
        final Country country = countryService.create(countryDto);
        return ResponseEntity.ok(country);
    }

    @PutMapping(value = "/updateCountry/{countryId}")
    public ResponseEntity<?> updateCountry(@PathVariable("countryId") long countryId,
                                           @RequestBody final CountryDto countryDto) {
        final Country newCountry = countryService.update(countryId, countryDto);
        return ResponseEntity.ok(newCountry);
    }

    @GetMapping("/deleteCountry/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        countryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
