package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.pagelist.PageList;
import com.exadel.sandbox.dto.response.event.EventDetailsResponse;
import com.exadel.sandbox.dto.response.event.EventResponse;
import com.exadel.sandbox.mappers.event.EventMapper;
import com.exadel.sandbox.mappers.location.LocationMapper;
import com.exadel.sandbox.model.vendorinfo.Event;
import com.exadel.sandbox.repository.UserRepository;
import com.exadel.sandbox.repository.event.EventRepository;
import com.exadel.sandbox.repository.location_repository.CityRepository;
import com.exadel.sandbox.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Locale;
import java.util.Optional;

@AllArgsConstructor
@Service
public class EventServiceImp implements EventService {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 10;
    private static final String DEFAULT_FIELD_SORT = "name";

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final LocationMapper mapper;
    private final EventMapper eventMapper;

    @Override
    public PageList<EventResponse> getAllEventsByUserId(Long userId, Integer pageNumber, Integer pageSize) {
        var city = cityRepository.findCityByUserId(userId);
        return getEventResponses(city.getId(), getPageNumber(pageNumber), getPageSize(pageSize));
    }

    @Override
    public PageList<EventResponse> getAllEventsByCityId(Long cityId, Integer pageNumber, Integer pageSize) {
        return getEventResponses(cityId, pageNumber, pageSize);
    }

    private PageList<EventResponse> getEventResponses(Long cityId, Integer pageNumber, Integer pageSize) {
        Page<Event> eventsPage = eventRepository.findEventByCityId(cityId, PageRequest.of(pageNumber, pageSize));
//        List<Event> events = eventRepository.findEventByCityId(cityId);
//
//        var formatter = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);

//        return new PageList<>(eventMapper.eventListToEventResponseList(eventsPage.getContent()),
//                PageRequest.of(eventsPage.getPageable().getPageNumber(),
//                        eventsPage.getPageable().getPageSize(),
//                        eventsPage.getPageable().getSort()),
//                eventsPage.getTotalElements());
//        final List<Event> content = eventsPage.getContent();
//        content.forEach(System.out::println);
//        eventMapper.eventListToEventResponseList(eventsPage.getContent()).forEach(System.out::println);
        return new PageList<>(eventMapper.eventListToEventResponseList(eventsPage.getContent()), eventsPage);
//        return eventsPage.stream()
//                .sorted(Comparator.comparing(Event::getDateEnd))
//                .map(event -> EventResponse.builder()
//                        .id(event.getId())
////                        .name(event.getName())
////                        .discount(event.getDiscount())
//                        .shortDescription(event.getDescription())
//                        .vendorName(event.getProducts().stream().iterator().next().getVendor().getName())
//                        .vendorId(event.getProducts().stream().iterator().next().getVendor().getId())
//                        .locations(mapper.listLocationToListShortLocation(event.getLocations()))
////                        .dateBegin(formatter.format(event.getDateBegin()))
//                        .dateEnd(formatter.format(event.getDateEnd()))
//                        .build()).collect(Collectors.toList());
    }


    @Override
    public EventDetailsResponse getEventById(Long eventId) {
        var formatter = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);

        Optional<Event> events = Optional.ofNullable(eventRepository.findEventById(eventId));

        return events.stream()
                .sorted(Comparator.comparing(Event::getDateEnd))
                .map(event -> EventDetailsResponse.builder()
                        .id(event.getId())
//                        .name(event.getName())
                        .shortDescription(event.getDescription())
                        .vendorName(event.getVendor().getName())
                        .vendorId(event.getVendor().getId())
                        .categoryName(event.getCategory().getName())
                        .categoryId(event.getCategory().getId())
//                        .price(event.getPrice())
                        .dateBegin(formatter.format(event.getDateBegin()))
                        .dateEnd(formatter.format(event.getDateEnd()))
                        .locations(mapper.setLocationToListLocation(event.getLocations()))
                        .detailedDescription(event.getDescription())
                        .build()).findFirst().orElseThrow(() -> new EntityNotFoundException(""));
    }

    private int getPageNumber(Integer pageNumber) {
        return pageNumber == null || pageNumber < 0 ? DEFAULT_PAGE_NUMBER : pageNumber;
    }

    private int getPageSize(Integer pageSize) {
        return pageSize == null || pageSize < 1 ? DEFAULT_PAGE_SIZE : pageSize;
    }
}
