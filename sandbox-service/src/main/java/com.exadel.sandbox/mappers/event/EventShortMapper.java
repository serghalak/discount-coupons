package com.exadel.sandbox.mappers.event;

import com.exadel.sandbox.dto.response.event.EventShortResponse;
import com.exadel.sandbox.mappers.location.LocationMapper;
import com.exadel.sandbox.model.vendorinfo.Event;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@AllArgsConstructor
@Component
public class EventShortMapper {

    private LocationMapper locationMapper;

    private ModelMapper mapper;

    public EventShortResponse eventToEventShortResponse(Event event) {
        var locations = locationMapper.setLocationToListShortLocation(event.getLocations());
        var eventShortResponse = Objects.isNull(event) ? null : mapper.map(event, EventShortResponse.class);
        eventShortResponse.setLocationsResponse(locations);

        return eventShortResponse;
    }

    public <T, R> R modelToEntityResponse(T t, Class<R> clazz) {
        return Objects.isNull(t) ? null : mapper.map(t, clazz);
    }
}