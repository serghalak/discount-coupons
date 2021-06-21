package com.exadel.sandbox.mappers.event;

import com.exadel.sandbox.dto.response.event.EventShortResponse;
import com.exadel.sandbox.dto.response.location.LocationShortResponse;
import com.exadel.sandbox.model.vendorinfo.Event;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class EventShortMapper {

    private ModelMapper mapper;

    public EventShortResponse eventToEventShortResponse(Event event) {
        var locations = event.getLocations()
                .stream()
                .map(l -> mapper.map(l, LocationShortResponse.class))
                .collect(Collectors.toList());
        var eventShortResponse = Objects.isNull(event) ? null : mapper.map(event, EventShortResponse.class);
        eventShortResponse.getLocations().addAll(locations);
        return eventShortResponse;
    }
}
