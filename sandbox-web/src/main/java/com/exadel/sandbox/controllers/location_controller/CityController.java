package com.exadel.sandbox.controllers.location_controller;

import com.exadel.sandbox.dto.request.CityRequest;
import com.exadel.sandbox.dto.response.city.CityResponse;
import com.exadel.sandbox.dto.response.user.AuthenticationResponse;
import com.exadel.sandbox.model.location.City;
import com.exadel.sandbox.security.utill.JwtUtil;
import com.exadel.sandbox.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/api/location")
public class CityController {

    private final CityService cityService;

    private final JwtUtil jwtUtil;

    @GetMapping("/allCities")
    public List<City> getAllCity() {
        return cityService.findAll();
    }

    @PostMapping(produces = {"application/json"},
            consumes = {"application/json"},
            path = "/newCity")
    public ResponseEntity<?> createCity(@RequestBody final CityRequest cityRequest) {

        final var cityResponse = cityService.create(cityRequest);

        return new ResponseEntity<>(cityResponse, HttpStatus.OK);
    }

    @GetMapping("/allActiveCities")
    public Set<CityResponse> findCitiesByEventStatusActive() {
        return cityService.findCitiesByEventStatusActive();
    }

    @GetMapping("/allFavoriteCities")
    public ResponseEntity<?> findCitiesByFavoriteEvent(@RequestHeader("Authorization") AuthenticationResponse authenticationResponse) {

        Long userId = jwtUtil.extractUserIdFromAuthResponse(authenticationResponse);
        var cities = cityService.findCitiesByFavoriteEvent(userId);

        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @GetMapping("/allCitiesByCountry")
    public List<City> getAllCityByCountry(@RequestParam(name = "countryName") String countryName) {
        return cityService.findCitiesByCountryNameOrderByName(countryName);
    }

}