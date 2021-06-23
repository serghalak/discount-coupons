package com.exadel.sandbox.controllers.location_controller;

import com.exadel.sandbox.dto.request.location.LocationRequest;
import com.exadel.sandbox.model.location.Location;
import com.exadel.sandbox.service.CountryService;
import com.exadel.sandbox.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/location")
public class LocationController {

    private final LocationService locationService;
    private final CountryService countryService;

    @GetMapping("/allLocations")
    public List<Location> getAllLocation() {
        return locationService.findAll();
    }

    @GetMapping(produces = {"application/json"}, path = "/allLocationsByCity")
    public List<Location> getAllLocationByCity(@RequestParam(name = "cityName") String cityName) {
        return locationService.getAllLocationByCity(cityName);
    }

    @GetMapping("/newLocation")
    public ResponseEntity<?> create() {
        return new ResponseEntity<>(countryService.findAll(), HttpStatus.OK);
    }

    @PostMapping(produces = {"application/json"},
            consumes = {"application/json"},
            path = "/newLocation")
    public ResponseEntity<?> createLocation(@RequestBody final LocationRequest locationRequest) {

        final var newLocation = locationService.create(locationRequest);

        return new ResponseEntity<>(newLocation, HttpStatus.OK);
    }

    @PutMapping(produces = {"application/json"},
            consumes = {"application/json"},
            path = "/updateLocation/{locationId}")
    public ResponseEntity<?> updateLocation(@PathVariable("locationId") Long locationId,
                                            @RequestBody final LocationRequest locationRequest) {

        final var newLocation = locationService.update(locationId, locationRequest);

        return new ResponseEntity<>(newLocation, HttpStatus.OK);
    }

}
