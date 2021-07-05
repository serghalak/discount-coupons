package com.exadel.sandbox.controllers;

import com.exadel.sandbox.dto.pagelist.PageList;
import com.exadel.sandbox.dto.request.FilterRequest;
import com.exadel.sandbox.dto.response.event.CustomEventResponse;
import com.exadel.sandbox.dto.response.event.EventDetailsResponse;
import com.exadel.sandbox.dto.response.user.AuthenticationResponse;
import com.exadel.sandbox.security.utill.JwtUtil;
import com.exadel.sandbox.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/event")
public class EventController {

    private final EventService eventService;
    private final JwtUtil jwtUtil;

    @GetMapping("/")
    public ResponseEntity<?> getAllEventsByCity(
            @RequestHeader("Authorization") AuthenticationResponse authResponse,
            @RequestParam(name = "city_id", required = false) Long cityId,
            @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", required = false) Integer pageSize
    ) {
        final PageList<CustomEventResponse> events = cityId == null ?
                eventService.getAllEventsByUserId(jwtUtil.extractUserIdFromAuthResponse(authResponse), pageNumber, pageSize) :
                eventService.getAllEventsByCityId(cityId, pageNumber, pageSize);

        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/by_filter")
    public ResponseEntity<?> getAllEventsByFilter(
            @RequestHeader("Authorization") AuthenticationResponse authResponse,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "0") Integer pageSize,
            @RequestBody FilterRequest filterRequest
    ) {
        final PageList<CustomEventResponse> events = eventService.getEventsByFilter(
                jwtUtil.extractUserIdFromAuthResponse(authResponse),
                filterRequest,
                pageNumber,
                pageSize);

        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<?> getEventById(@PathVariable long eventId) {
        var event = eventService.getEventById(eventId);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @GetMapping("/by_description")
    public ResponseEntity<?> getAllEventsByDescription(
            @RequestHeader("Authorization") AuthenticationResponse authResponse,
            @RequestParam(name = "city_id", required = false) Long cityId,
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "0") Integer pageSize
    ) {
        return new ResponseEntity<>(eventService.getAllEventsByDescription(cityId, search, pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/events")
    public PageList<EventDetailsResponse> getAllEvents(
            @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", required = false) Integer pageSize
    ) {
        return eventService.getAll(pageNumber, pageSize);
    }


    /*TODO fixed endpoint*/
//    @DeleteMapping(path = {"/{eventId}"})
//    public ResponseEntity<?> deleteEvent(@PathVariable("eventId") Long eventId) {
//
//        return eventService.deleteEventById(eventId) ?
//                ResponseEntity.status(HttpStatus.OK).build() :
//                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//
//    }

    /*TODO fixed endpoint*/
//    @PostMapping(produces = {"application/json"},
//            consumes = {"application/json"},
//            path = {"/{vendorId}"})
//    public ResponseEntity<?> createEvent(@PathVariable("vendorId") Long vendorId,
//                                         @Valid @RequestBody EventRequest eventRequest) {
//        return new ResponseEntity<>(eventService.saveEvent(vendorId, eventRequest), HttpStatus.OK);
//    }
}
