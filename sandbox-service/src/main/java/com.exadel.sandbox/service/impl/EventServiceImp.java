package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.response.event.EventDetailsResponse;
import com.exadel.sandbox.dto.response.event.EventResponse;
import com.exadel.sandbox.model.vendorinfo.Event;
import com.exadel.sandbox.repository.EventRepository;
import com.exadel.sandbox.repository.UserRepository;
import com.exadel.sandbox.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class EventServiceImp implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Override
    public List<EventResponse> getAllEventsByUserLocation(Principal principal) {
        var user = userRepository.findByEmail(principal.getName());
        List<Event> events = eventRepository.findEventByLocations(user.getLocation().getCity());
        var formatter = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);

        return events.stream()
                .sorted(Comparator.comparing(Event::getDateEnd))
                .map(event -> EventResponse.builder()
                        .id(event.getId())
                        .name(event.getName())
                        .discount(event.getDiscount())
                        .shortDescription(event.getDescription())
                        .vendor(event.getProducts().stream().iterator().next().getVendor().getName())
                        .country(event.getLocations().stream().iterator().next().getCity().getCountry().getName())
                        .city(event.getLocations().stream().iterator().next().getCity().getName())
                        .dateBegin(formatter.format(event.getDateBegin()))
                        .dateEnd(formatter.format(event.getDateEnd()))
                        .build()).collect(Collectors.toList());
    }

    @Override
    public List<EventDetailsResponse> getAllEventsById(Long eventId) {
        var formatter = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);

        Optional<Event> events = Optional.ofNullable(eventRepository.findEventById(eventId));

        return events.stream()
                .sorted(Comparator.comparing(Event::getDateEnd))
                .map(event -> EventDetailsResponse.builder()
                        .id(event.getId())
                        .name(event.getName())
                        .shortDescription(event.getDescription())
                        .vendor(event.getProducts().stream().iterator().next().getVendor().getName())
                        .price(event.getPrice())
                        .dateBegin(formatter.format(event.getDateBegin()))
                        .dateEnd(formatter.format(event.getDateEnd()))
                        .discount(event.getDiscount())
                        .country(event.getLocations().stream().iterator().next().getCity().getCountry().getName())
                        .city(event.getLocations().stream().iterator().next().getCity().getName())
                        .detailedDescription(event.getProducts().stream().iterator().next().getDescription())
                        .build()).collect(Collectors.toList());
    }
}
