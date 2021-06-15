package com.exadel.sandbox.controllers.location_controller;

import com.exadel.sandbox.dto.LocationDto;
import com.exadel.sandbox.model.location.Location;
import com.exadel.sandbox.service.CountryService;
import com.exadel.sandbox.service.LocationService;
import com.exadel.sandbox.ui.mappers.UILocationMappers;
import com.exadel.sandbox.ui.request.LocationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;
    private final CountryService countryService;
    private final UILocationMappers uiLocationMappers;

    @Autowired
    public LocationController(LocationService locationService, CountryService countryService, UILocationMappers uiLocationMappers) {
        this.locationService = locationService;
        this.countryService = countryService;
        this.uiLocationMappers = uiLocationMappers;
    }

    @GetMapping("/getAllLocation")
    public ResponseEntity<?> getAllLocation() {
        return new ResponseEntity<>(locationService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/getAllLocationByCity")
    public ResponseEntity<?> getAllLocationByCity(@RequestParam(name = "cityName") String cityName) {
        return new ResponseEntity<>(locationService.getAllLocationByCity(cityName), HttpStatus.OK);
    }

    @GetMapping("/createLocation")
    public ResponseEntity<?> create() {
        return new ResponseEntity<>(countryService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/createLocation")
    public ResponseEntity<?> createLocation(@RequestBody final LocationRequest locationRequest) {
        final Location newLocation = locationService.create(uiLocationMappers.locationRequestToLocationDto(locationRequest));
        return ResponseEntity.ok(newLocation);
    }

    @PutMapping(value = "/updateLocation/{locationId}")
    public ResponseEntity<?> updateLocation(@PathVariable("locationId") Long locationId,
                                            @RequestBody final LocationDto locationDto) {
        final Location newLocation = locationService.update(locationId, locationDto);
        return ResponseEntity.ok(newLocation);
    }


}
