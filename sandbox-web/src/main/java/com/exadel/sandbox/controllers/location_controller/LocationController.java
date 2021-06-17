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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping(produces = {"application/json"}, path = "/allLocations")
    public ResponseEntity<?> getAllLocation() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getPrincipal());
        System.out.println(authentication.getDetails());
        System.out.println(authentication.getName());
        System.out.println(authentication.getCredentials());
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
        final LocationDto newLocation = locationService.create(mapper.map(locationRequest, LocationDto.class));
        return ResponseEntity.ok(mapper.map(newLocation, LocationResponse.class));
    }

    @PutMapping(produces = {"application/json"},
            consumes = {"application/json"},
            path = "/updateLocation/{locationId}")
    public ResponseEntity<?> updateLocation(@PathVariable("locationId") Long locationId,
                                            @RequestBody final LocationDto locationDto) {
        final LocationDto newLocation = locationService.update(locationId, locationDto);
        return ResponseEntity.ok(mapper.map(newLocation, LocationResponse.class));
    }

}
