package com.exadel.sandbox.controllers.location_controller;

import com.exadel.sandbox.dto.LocationDto;
import com.exadel.sandbox.service.CountryService;
import com.exadel.sandbox.service.LocationService;
import com.exadel.sandbox.ui.request.LocationRequest;
import com.exadel.sandbox.ui.response.LocationResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;
    private final CountryService countryService;
    private final ModelMapper mapper;

    @Autowired
    public LocationController(LocationService locationService, CountryService countryService, ModelMapper modelMapper) {
        this.locationService = locationService;
        this.countryService = countryService;
        this.mapper = modelMapper;
    }

    @GetMapping("/allLocations")
    public ResponseEntity<?> getAllLocation() {
        return new ResponseEntity<>(locationService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/allLocationsByCity")
    public ResponseEntity<?> getAllLocationByCity(@RequestParam(name = "cityName") String cityName) {
        return new ResponseEntity<>(locationService.getAllLocationByCity(cityName), HttpStatus.OK);
    }

    @GetMapping("/newLocation")
    public ResponseEntity<?> create() {
        return new ResponseEntity<>(countryService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/newLocation")
    public ResponseEntity<?> createLocation(@RequestBody final LocationRequest locationRequest) {
        final LocationDto newLocation = locationService.create(mapper.map(locationRequest, LocationDto.class));
        return ResponseEntity.ok(mapper.map(newLocation, LocationResponse.class));
    }

    @PutMapping(value = "/updateLocation/{locationId}")
    public ResponseEntity<?> updateLocation(@PathVariable("locationId") Long locationId,
                                            @RequestBody final LocationDto locationDto) {
        final LocationDto newLocation = locationService.update(locationId, locationDto);
        return ResponseEntity.ok(mapper.map(newLocation, LocationResponse.class));
    }

}
