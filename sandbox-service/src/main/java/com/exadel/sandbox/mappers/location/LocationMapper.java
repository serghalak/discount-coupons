package com.exadel.sandbox.mappers.location;

import com.exadel.sandbox.dto.response.location.LocationResponse;
import com.exadel.sandbox.dto.response.location.LocationShortResponse;
import com.exadel.sandbox.model.location.Location;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
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

}
