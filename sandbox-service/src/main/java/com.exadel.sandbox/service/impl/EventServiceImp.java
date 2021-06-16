package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.EventDetailedDto;
import com.exadel.sandbox.dto.EventShortDto;
import com.exadel.sandbox.model.user.User;
import com.exadel.sandbox.model.vendorinfo.Event;
import com.exadel.sandbox.repository.EventRepository;
import com.exadel.sandbox.repository.UserRepository;
import com.exadel.sandbox.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class EventServiceImp implements EventService {

    private EventRepository eventRepository;
    private UserRepository userRepository;

    @Override
    public List<EventShortDto> getAllEventsByUserLocation(Principal principal) {
        User user = userRepository.findByEmail(principal.getName());
        List<Event> events = eventRepository.findEventByLocations(user.getLocation());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);

        return events.stream()
                .sorted((event1, event2) -> event1.getDateEnd().compareTo(event2.getDateEnd()))
                .map(event -> EventShortDto.builder()
                        .id(event.getId())
                        .name(event.getName())
                        .discount(event.getDiscount())
                        .shortDescription(event.getDescription())
                        .vendor(event.getProducts().stream().iterator().next().getVendor().getName())
                        .country(event.getLocations().stream().iterator().next().getCity().getCountry().getName())
                        .city(event.getLocations().stream().iterator().next().getCity().getName())
                        .location(event.getLocations().iterator().next().getCity().getLocations())
                        .dateBegin(formatter.format(event.getDateBegin()))
                        .dateEnd(formatter.format(event.getDateEnd()))
                        .build()).collect(Collectors.toList());
    }

    @Override
    public List<EventDetailedDto> getAllEventsById(Long eventId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);

        Optional<Event> events = Optional.ofNullable(eventRepository.findEventById(eventId));


        return events.stream()
                .sorted((event1, event2) -> event1.getDateEnd().compareTo(event2.getDateEnd()))
                .map(event -> EventDetailedDto.builder()
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
                        .location(event.getLocations().iterator().next().getCity().getLocations())
                        .detailedDescription(event.getProducts().stream().iterator().next().getDescription())
                        .build()).collect(Collectors.toList());
    }
}
