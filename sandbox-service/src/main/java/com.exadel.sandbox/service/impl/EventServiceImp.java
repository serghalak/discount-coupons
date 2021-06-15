package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.EventDto;
import com.exadel.sandbox.model.vendorinfo.Event;
import com.exadel.sandbox.repository.EventRepository;
import com.exadel.sandbox.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class EventServiceImp implements EventService {

    private EventRepository eventRepository;

    @Override
    public List<EventDto> getAllEvents() {
        List<Event> events = eventRepository.findAll(Sort.by("dateEnd"));
       return events.stream().map(event -> EventDto.builder()
               .id(event.getId())
               .name(event.getName())
               .shortDescription(event.getDescription())
               .vendor(event.getProducts().stream().iterator().next().getVendor().getName())
               .price(event.getPrice())
               .discount(event.getDiscount())
               .status(event.getStatus().name())
               .online(String.valueOf(event.isOnline()))
               .country(event.getLocations().stream().iterator().next().getCity().getCountry().getName())
               .city(event.getLocations().stream().iterator().next().getCity().getName())
               .street(event.getLocations().stream().iterator().next().getStreet())
               .streetNumber(event.getLocations().stream().iterator().next().getNumber())
               .latitude(event.getLocations().stream().iterator().next().getLatitude())
               .longitude(event.getLocations().stream().iterator().next().getLongitude())
               .dateBegin(event.getDateBegin())
               .dateEnd(event.getDateEnd())
               .quantityOfCoupons(event.getTotalCount())
               .maxQuantityPerCustomer(event.getLimitation())
               .detailedDescription(event.getProducts().stream().iterator().next().getDescription())
               .build()).collect(Collectors.toList());
    }

}
