package com.exadel.sandbox.mappers.location;

import com.exadel.sandbox.dto.response.location.LocationShortResponse;
import com.exadel.sandbox.model.location.Location;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@AllArgsConstructor
@Component
public class LocationShortMapper {

    private ModelMapper mapper;

    public LocationShortResponse locationToLocationShortResponse (Location location){
        var locationShortResponse = Objects.isNull(location) ? null : mapper.map(location, LocationShortResponse.class);
        locationShortResponse.setCity(location.getCity().getName());
        locationShortResponse.setCountry(location.getCity().getCountry().getName());
        return locationShortResponse;
    }

}
