package com.exadel.sandbox.controllers.location_controller;

import com.exadel.sandbox.dto.request.LocationDto;
import com.exadel.sandbox.model.location.Location;
import com.exadel.sandbox.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/getAllLocation")
    public ResponseEntity<?> getAllLocation() {
        return new ResponseEntity<>(locationService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/getAllLocationByCity")
    public ResponseEntity<?> getAllLocationByCity(@RequestParam(name = "cityName") String cityName) {
        return new ResponseEntity<>(locationService.getAllLocationByCity(cityName), HttpStatus.OK);
    }

    @PostMapping("/createLocation")
    public ResponseEntity<?> createLocation(@RequestBody final LocationDto locationDto) {
        final Location newLocation = locationService.create(locationDto);
        return ResponseEntity.ok(newLocation);
    }

    @PutMapping(value = "/updateLocation/{locationId}")
    public ResponseEntity<?> updateLocation(@PathVariable("locationId") Long locationId,
                                            @RequestBody final LocationDto locationDto) {
        final Location newLocation = locationService.update(locationId, locationDto);
        return ResponseEntity.ok(newLocation);
    }


}
