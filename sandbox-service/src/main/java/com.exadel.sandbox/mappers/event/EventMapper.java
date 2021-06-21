package com.exadel.sandbox.mappers.event;

import com.exadel.sandbox.dto.request.event.EventRequest;
import com.exadel.sandbox.dto.response.event.EventResponse;
import com.exadel.sandbox.model.vendorinfo.Event;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@AllArgsConstructor
@Component
public class EventMapper {

    private ModelMapper mapper;

    public Event eventRequestToEvent(EventRequest eventRequest) {
        return Objects.isNull(Objects.requireNonNull(eventRequest)) ? null : mapper.map(eventRequest, Event.class);

    }

    public EventResponse eventToEventResponse(Event event) {
        return Objects.isNull(event) ? null : mapper.map(event, EventResponse.class);
    }
}