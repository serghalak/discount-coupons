package com.exadel.sandbox.controllers;

import com.exadel.sandbox.dto.EventDto;
import com.exadel.sandbox.model.vendorinfo.Event;
import com.exadel.sandbox.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/event")
public class EventController {

    private EventService eventService;


    @GetMapping("/getAll")
    public ResponseEntity<?> getAllEvents() {
        List<EventDto> eventList = eventService.getAllEvents();
        return new ResponseEntity<>(eventList, HttpStatus.OK);
    }
}
