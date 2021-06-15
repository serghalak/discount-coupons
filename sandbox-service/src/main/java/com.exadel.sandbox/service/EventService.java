package com.exadel.sandbox.service;

import com.exadel.sandbox.dto.EventDto;
import com.exadel.sandbox.model.vendorinfo.Event;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EventService {

    List<EventDto> getAllEvents();
}
