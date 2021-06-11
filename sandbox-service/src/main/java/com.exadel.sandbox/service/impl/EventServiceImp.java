package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.model.vendorinfo.Event;
import com.exadel.sandbox.repository.EventRepository;
import com.exadel.sandbox.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class EventServiceImp implements EventService {

    private EventRepository eventRepository;

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}
