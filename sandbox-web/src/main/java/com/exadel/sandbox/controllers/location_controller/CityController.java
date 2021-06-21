package com.exadel.sandbox.controllers.location_controller;

import com.exadel.sandbox.dto.CityDto;
import com.exadel.sandbox.security.utill.JwtUtil;
import com.exadel.sandbox.service.CityService;
import com.exadel.sandbox.ui.response.AuthenticationResponse;
import com.exadel.sandbox.ui.response.CityResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/location")
public class CityController {

    private final CityService cityService;
    private final ModelMapper mapper;
    private final JwtUtil jwtUtil;

    @Autowired
    public CityController(CityService cityService, ModelMapper mapper, JwtUtil jwtUtil) {
        this.cityService = cityService;
        this.mapper = mapper;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping(produces = {"application/json"}, path = "/allCities")
    public ResponseEntity<?> getAllCity() {
        return new ResponseEntity<>(cityService.findAll(), HttpStatus.OK);
    }

    @GetMapping(produces = {"application/json"}, path = "/allActiveCities")
    public ResponseEntity<?> findCitiesByEventStatusActive() {
        return ResponseEntity.ok()
                .body(mapList(cityService.findCitiesByEventStatusActive(),
                        CityResponse.class));
    }

    @GetMapping(produces = {"application/json"}, path = "/allFavoriteCities")
    public ResponseEntity<?> findCitiesByFavoriteEvent(
            @RequestHeader("Authorization") AuthenticationResponse authenticationResponse
    ) {
        Long userId = Long.parseLong(getIdFromHeadersJwt(authenticationResponse));
        Set<CityDto> cityDtos = cityService.findCitiesByFavoriteEvent(userId);
        return ResponseEntity.ok()
                .body(mapList(cityDtos,
                        CityResponse.class));
    }


    @GetMapping(produces = {"application/json"}, path = "/allCitiesByCountry")
    public ResponseEntity<?> getAllCityByCountry(@RequestParam(name = "countryName") String countryName) {
        return new ResponseEntity<>(cityService.findCitiesByCountryNameOrderByName(countryName), HttpStatus.OK);
    }

    private <S, T> List<T> mapList(Set<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> mapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

    private String getIdFromHeadersJwt(AuthenticationResponse authenticationResponse) {
        return jwtUtil.extractUserId(authenticationResponse.getJwt().substring(7));
    }

}
