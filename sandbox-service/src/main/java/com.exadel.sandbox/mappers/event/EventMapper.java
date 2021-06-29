package com.exadel.sandbox.mappers.event;

import com.exadel.sandbox.dto.request.event.EventRequest;
import com.exadel.sandbox.dto.response.event.EventResponse;
import com.exadel.sandbox.mappers.location.LocationMapper;
import com.exadel.sandbox.model.vendorinfo.Event;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class EventMapper {

    private final ModelMapper mapper;
    private final LocationMapper locMapper;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);

    public Event eventRequestToEvent(EventRequest eventRequest) {
        return Objects.isNull(Objects.requireNonNull(eventRequest)) ? null : mapper.map(eventRequest, Event.class);
    }

    public EventResponse eventToEventResponse(Event event) {
        return Objects.isNull(event) ? null : mapper.map(event, EventResponse.class);
    }

    public List<EventResponse> eventListToEventResponseList(List<Event> events) {
        return events.stream()
                .sorted(Comparator.comparing(Event::getDateEnd))
                .peek(System.out::println)
                .map(event -> EventResponse.builder()
                        .id(event.getId())
//                        .name(event.getName())
//                        .discount(event.getDiscount())
                        .shortDescription(event.getDescription())
                        .vendorName(event.getVendor().getName())
                        .vendorId(event.getVendor().getId())
                        .locations(locMapper.setLocationToListShortLocation(event.getLocations()))
//                        .dateBegin(formatter.format(event.getDateBegin()))
                        .dateEnd(formatter.format(event.getDateEnd()))
                        .build())
                .collect(Collectors.toList());
    }
}