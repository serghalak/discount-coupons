package com.exadel.sandbox.controllers.location_controller;

import com.exadel.sandbox.dto.request.location.LocationRequest;
import com.exadel.sandbox.service.CountryService;
import com.exadel.sandbox.service.LocationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    private final LocationService locationService;
    private final CountryService countryService;

    @Autowired
    public LocationController(LocationService locationService, CountryService countryService, ModelMapper modelMapper) {
        this.locationService = locationService;
        this.countryService = countryService;
    }

    @GetMapping(produces = {"application/json"}, path = "/allLocations")
    public ResponseEntity<?> getAllLocation() {

        return new ResponseEntity<>(locationService.findAll(), HttpStatus.OK);
    }

    @GetMapping(produces = {"application/json"}, path = "/allLocationsByCity")
    public ResponseEntity<?> getAllLocationByCity(@RequestParam(name = "cityName") String cityName) {
        return new ResponseEntity<>(locationService.getAllLocationByCity(cityName), HttpStatus.OK);
    }

    @GetMapping(produces = {"application/json"}, path = "/newLocation")
    public ResponseEntity<?> create() {
        return new ResponseEntity<>(countryService.findAll(), HttpStatus.OK);
    }

    @PostMapping(produces = {"application/json"},
            consumes = {"application/json"},
            path = "/newLocation")
    public ResponseEntity<?> createLocation(@RequestBody final LocationRequest locationRequest) {
        final var newLocation = locationService.create(locationRequest);

        return ResponseEntity.ok(newLocation);
    }

    @PutMapping(produces = {"application/json"},
            consumes = {"application/json"},
            path = "/updateLocation/{locationId}")
    public ResponseEntity<?> updateLocation(@PathVariable("locationId") Long locationId,
                                            @RequestBody final LocationRequest locationRequest) {
        final var newLocation = locationService.update(locationId, locationRequest);

        return ResponseEntity.ok(newLocation);
    }

}
