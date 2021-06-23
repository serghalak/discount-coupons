package com.exadel.sandbox.controllers;

import com.exadel.sandbox.security.utill.JwtUtil;
import com.exadel.sandbox.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {


    private final UserService userService;
    private final JwtUtil jwtUtil;

    @GetMapping(path = "/current")
    ResponseEntity<?> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok().body(userService.findByName(authentication.getName()));
    }

    @PostMapping(path = "/addEvent/toOrder/{eventId}")
    ResponseEntity<?> addEventToUserOrder(@PathVariable Long eventId, @RequestHeader HttpHeaders httpHeaders) {

        return ResponseEntity.ok()
                .body(userService.saveEventToOrder(eventId, retrieveUserId(httpHeaders)));
    }

    @PostMapping(path = "/addEvent/toSaved/{eventId}")
    ResponseEntity<?> addEventToSaved(@RequestHeader HttpHeaders httpHeaders,
                                      @PathVariable Long eventId) {

        return ResponseEntity.ok()
                .body(userService.saveEventToSaved(eventId,
                        retrieveUserId(httpHeaders)));
    }

    @DeleteMapping(path = "/removeEvent/fromSaved/{eventId}")
    ResponseEntity<?> removeEventFromSaved(@RequestHeader HttpHeaders httpHeaders,
                                           @PathVariable Long eventId) {
        userService.removeEventFromSaved(eventId,
                retrieveUserId(httpHeaders));
        return ResponseEntity.ok().body("Event successfully removed from User saved ");
    }

    @DeleteMapping(path = "/removeEvent/fromOrder/{eventId}")
    ResponseEntity<?> removeEventFromOrder(@RequestHeader HttpHeaders httpHeaders,
                                           @PathVariable Long eventId) {

        userService.removeEventFromOrder(eventId,
                retrieveUserId(httpHeaders));
        return ResponseEntity.ok().body("Event successfully removed from User order ");
    }

    @GetMapping(path = "/allEvents/fromUserOrder")
    ResponseEntity<?> getAllEventsFromUserOrder(@RequestHeader HttpHeaders httpHeaders) {
        return ResponseEntity.ok().body(userService.getAllFromOrder(retrieveUserId(httpHeaders)));
    }

    @GetMapping(path = "/allEvents/fromUserSaved")
    ResponseEntity<?> getAllEventsFromUserSaved(@RequestHeader HttpHeaders httpHeaders) {
        return ResponseEntity.ok().body(userService.getAllFromSaved(retrieveUserId(httpHeaders)));
    }

    private Long retrieveUserId(HttpHeaders httpHeaders) {
        String token = httpHeaders.get("Authorization").toString();
        return Long.parseLong(jwtUtil.extractUserId(token.substring(7, token.length() - 1)));
    }
}