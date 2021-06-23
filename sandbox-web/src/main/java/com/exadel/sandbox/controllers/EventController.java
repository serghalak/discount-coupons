package com.exadel.sandbox.controllers;

import com.exadel.sandbox.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@AllArgsConstructor
@RestController
@RequestMapping("/api/event")
public class EventController {

    private final EventService eventService;

    @GetMapping("/")
    public ResponseEntity<?> getAllEventsByUserLocation(Principal principal) {
        var events = eventService.getAllEventsByUserLocation(principal);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<?> getEventById(@PathVariable long eventId) {
        var events = eventService.getEventById(eventId);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }
}
