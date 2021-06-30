package com.exadel.sandbox.mappers.event;

import com.exadel.sandbox.dto.request.event.EventRequest;
import com.exadel.sandbox.dto.response.event.EventDetailsResponse;
import com.exadel.sandbox.dto.response.event.EventResponse;
import com.exadel.sandbox.mappers.location.LocationMapper;
import com.exadel.sandbox.mappers.tag.TagMapper;
import com.exadel.sandbox.model.vendorinfo.Event;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class EventMapper {

    private final ModelMapper mapper;
    private final LocationMapper locMapper;
    private final TagMapper tagMapper;

    public Event eventRequestToEvent(EventRequest eventRequest) {
        return Objects.isNull(eventRequest) ? null : mapper.map(eventRequest, Event.class);
    }

    public EventResponse eventToEventResponse(Event event) {
        return Objects.isNull(event) ? null : mapper.map(event, EventResponse.class);
    }

    public EventDetailsResponse eventToEventDetailResponse(Event event) {
        return EventDetailsResponse.builder()
                .id(event.getId())
                .shortDescription(event.getDescription())
                .vendorName(event.getVendor().getName())
                .vendorId(event.getVendor().getId())
                .categoryName(event.getCategory().getName())
                .categoryId(event.getCategory().getId())
                .dateBegin(event.getDateBegin())
                .dateEnd(event.getDateEnd())
                .tags(tagMapper.setTagToSetTagResponse(event.getTags()))
                .locations(locMapper.setLocationToListLocation(event.getLocations()))
                .detailedDescription(event.getDescription())
                .build();
    }

    public List<EventResponse> eventListToEventResponseList(List<Event> events) {
        return events.stream()
                .sorted(Comparator.comparing(Event::getDateEnd))
                .map(event -> EventResponse.builder()
                        .id(event.getId())
                        .shortDescription(event.getDescription())
                        .fullDescription(event.getFullDescription())
                        .vendorName(event.getVendor().getName())
                        .vendorId(event.getVendor().getId())
                        .locations(locMapper.setLocationToListShortLocation(event.getLocations()))
                        .dateBegin(event.getDateBegin())
                        .dateEnd(event.getDateEnd())
                        .build())
                .collect(Collectors.toList());
    }
}
