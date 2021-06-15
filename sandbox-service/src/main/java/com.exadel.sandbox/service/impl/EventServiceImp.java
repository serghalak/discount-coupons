package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.EventDto;
import com.exadel.sandbox.model.user.User;
import com.exadel.sandbox.model.vendorinfo.Event;
import com.exadel.sandbox.repository.EventRepository;
import com.exadel.sandbox.repository.UserRepository;
import com.exadel.sandbox.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class EventServiceImp implements EventService {

    private EventRepository eventRepository;
    private UserRepository userRepository;

    @Override
    public List<EventDto> getAllEventsByUserLocation(Principal principal) {
        User user = userRepository.findByEmail(principal.getName());
        List<Event> events = eventRepository.findAll(Sort.by("dateEnd"));
        return events.stream().filter(event->event.getLocations().stream().iterator().next().getCity()==user.getLocation().getCity())
                .map(event -> EventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .shortDescription(event.getDescription())
                .vendor(event.getProducts().stream().iterator().next().getVendor().getName())
                .price(event.getPrice())
                .discount(event.getDiscount())
                .country(event.getLocations().stream().iterator().next().getCity().getCountry().getName())
                .city(event.getLocations().stream().iterator().next().getCity().getName())
                .status(event.getStatus().name())
                .online(String.valueOf(event.isOnline()))
                .location(event.getLocations().iterator().next().getCity().getLocations())
                .dateBegin(event.getDateBegin())
                .dateEnd(event.getDateEnd())
                .quantityOfCoupons(event.getTotalCount())
                .maxQuantityPerCustomer(event.getLimitation())
                .detailedDescription(event.getProducts().stream().iterator().next().getDescription())
                .build()).collect(Collectors.toList());
    }

}
