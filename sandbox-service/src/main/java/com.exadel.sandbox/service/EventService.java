package com.exadel.sandbox.service;

import com.exadel.sandbox.model.vendorinfo.Event;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {

    List<Event> getAllEvents();
}
