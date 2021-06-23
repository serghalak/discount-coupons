package com.exadel.sandbox.controllers;

import com.exadel.sandbox.dto.response.user.AuthenticationResponse;
import com.exadel.sandbox.security.utill.JwtUtil;
import com.exadel.sandbox.service.UserService;
import lombok.AllArgsConstructor;
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
    ResponseEntity<?> addEventToUserOrder(
            @PathVariable Long eventId,
            @RequestHeader("Authorization") AuthenticationResponse authenticationResponse) {

        return ResponseEntity.ok()
                .body(userService.saveEventToOrder(eventId,
                        jwtUtil.extractUserIdFromAuthResponse(authenticationResponse)));
    }

    @PostMapping(path = "/addEvent/toSaved/{eventId}")
    ResponseEntity<?> addEventToSaved(
            @RequestHeader("Authorization") AuthenticationResponse authenticationResponse,
            @PathVariable Long eventId) {

        return ResponseEntity.ok()
                .body(userService.saveEventToSaved(eventId,
                        jwtUtil.extractUserIdFromAuthResponse(authenticationResponse)));
    }

    @DeleteMapping(path = "/removeEvent/fromSaved/{eventId}")
    ResponseEntity<?> removeEventFromSaved(
            @RequestHeader("Authorization") AuthenticationResponse authenticationResponse,
            @PathVariable Long eventId) {
        userService.removeEventFromSaved(eventId,
                jwtUtil.extractUserIdFromAuthResponse(authenticationResponse));
        return ResponseEntity.ok().body("Event successfully removed from User saved ");
    }

    @DeleteMapping(path = "/removeEvent/fromOrder/{eventId}")
    ResponseEntity<?> removeEventFromOrder(
            @RequestHeader("Authorization") AuthenticationResponse authenticationResponse,
            @PathVariable Long eventId) {

        userService.removeEventFromOrder(eventId,
                jwtUtil.extractUserIdFromAuthResponse(authenticationResponse));
        return ResponseEntity.ok()
                .body("Event successfully removed from User order ");
    }

    @GetMapping(path = "/allEvents/fromUserOrder")
    ResponseEntity<?> getAllEventsFromUserOrder(
            @RequestHeader("Authorization") AuthenticationResponse authenticationResponse) {
        return ResponseEntity.ok()
                .body(userService.getAllFromOrder(
                        jwtUtil.extractUserIdFromAuthResponse(authenticationResponse)));
    }

    @GetMapping(path = "/allEvents/fromUserSaved")
    ResponseEntity<?> getAllEventsFromUserSaved
            (@RequestHeader("Authorization") AuthenticationResponse authenticationResponse) {
        return ResponseEntity.ok()
                .body(userService.getAllFromSaved(
                        jwtUtil.extractUserIdFromAuthResponse(authenticationResponse)));
    }
}