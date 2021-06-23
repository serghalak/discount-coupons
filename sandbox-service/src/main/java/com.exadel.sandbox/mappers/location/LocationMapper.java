package com.exadel.sandbox.mappers.location;

import com.exadel.sandbox.dto.response.location.LocationShortResponse;
import com.exadel.sandbox.model.location.Location;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class LocationMapper {

    private final ModelMapper mapper;

    public LocationShortResponse locationToLocationShortResponse(Location location) {
        if (location == null) {
            return null;
        }

        var locationShortResponse = mapper.map(location, LocationShortResponse.class);
        locationShortResponse.setCountryName(location.getCity().getCountry().getName());

        return locationShortResponse;
    }

    public Set<LocationShortResponse> listLocationToListShortLocation(Set<Location> location) {
        if (location == null || location.isEmpty()) {
            return Collections.emptySet();
        }

        return location.stream()
                .map(this::locationToLocationShortResponse)
                .collect(Collectors.toSet());
    }

}
