package com.exadel.sandbox.mappers.location;

import com.exadel.sandbox.dto.response.location.LocationResponse;
import com.exadel.sandbox.dto.response.location.LocationResponseByCity;
import com.exadel.sandbox.dto.response.location.LocationShortResponse;
import com.exadel.sandbox.model.location.Location;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class LocationMapper {

    private final ModelMapper mapper;

    public LocationResponse locationToLocationResponse(Location location) {
        return Objects.isNull(location) ? null : mapper.map(location, LocationResponse.class);
    }

    public Set<LocationResponse> setLocationToListLocation(Set<Location> locations) {
        if (locations == null || locations.isEmpty()) {
            return Collections.emptySet();
        }

        return locations.stream()
                .map(this::locationToLocationResponse)
                .collect(Collectors.toSet());
    }

    public LocationShortResponse locationToLocationShortResponse(Location location) {
        if (location == null) {
            return null;
        }

        var locationShortResponse = mapper.map(location, LocationShortResponse.class);
        locationShortResponse.setCountryName(location.getCity().getCountry().getName());

        return locationShortResponse;
    }

    public Set<LocationShortResponse> setLocationToListShortLocation(Set<Location> locations) {
        if (locations == null || locations.isEmpty()) {
            return Collections.emptySet();
        }

        return locations.stream()
                .map(this::locationToLocationShortResponse)
                .collect(Collectors.toSet());
    }

    public Set<LocationShortResponse> setLocationToListCustomLocationResponse(Set<Location> locations) {
        if (locations == null || locations.isEmpty()) {
            return Collections.emptySet();
        }


        return locations.stream()
                .map(this::locationToLocationShortResponse)
                .collect(Collectors.toSet());
    }

    public LocationResponseByCity setLocationToListLocationResponseByCity(Set<Location> locations, Long cityId) {
        final LocationResponseByCity locResponseByCity = new LocationResponseByCity();

        final var location = locations.stream()
                .filter(loc -> loc.getCity().getId().equals(cityId))
                .findFirst()
                .orElseThrow();

        locResponseByCity.setCountryName(location.getCity().getCountry().getName());
        locResponseByCity.getAddresses().put(location.getCity().getName(), getAddresses(locations, cityId));

        return locResponseByCity;
    }

    private Set<String> getAddresses(Set<Location> locations, Long cityId) {
        return locations.stream()
                .filter(loc -> loc.getCity().getId().equals(cityId))
                .map(loc -> loc.getStreet() + " " + loc.getNumber())
                .collect(Collectors.toSet());
    }

    public LocationResponseByCity setLocationToListLocationResponseByCountry(Set<Location> locations, Long countryId) {
        final LocationResponseByCity locResponseByCity = new LocationResponseByCity();

        final var location = locations.stream()
                .filter(loc -> loc.getCity().getCountry().getId().equals(countryId))
                .findFirst()
                .orElseThrow();

        locResponseByCity.setCountryName(location.getCity().getCountry().getName());

        final Map<String, Set<String>> collect1 = locations.stream()
                .filter(loc -> loc.getCity().getCountry().getId().equals(countryId))
                .collect(Collectors.groupingBy(loc -> loc.getCity().getName(),
                        Collectors.mapping(loc -> loc.getStreet() + " " + loc.getNumber(), Collectors.toSet())));
        locResponseByCity.setAddresses(collect1);

        return locResponseByCity;
    }
}
