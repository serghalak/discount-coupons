package com.exadel.sandbox.controllers;

import com.exadel.sandbox.dto.response.user.AuthenticationResponse;
import com.exadel.sandbox.security.utill.JwtUtil;
import com.exadel.sandbox.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @GetMapping(path = "/current")
    public ResponseEntity<?> getUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok().body(userService.findByName(authentication.getName()));
    }

    @PostMapping(path = "/addEvent/toOrder/{eventId}")
    public ResponseEntity<?> addEventToUserOrder(
            @PathVariable Long eventId,
            @RequestHeader("Authorization") AuthenticationResponse authResponse) {

        return ResponseEntity.ok()
                .body(userService.saveEventToOrder(
                        jwtUtil.extractUserIdFromAuthResponse(authResponse),
                        eventId,
                        jwtUtil.extractEmailFromAuthResponse(authResponse)));
    }

    @PostMapping(path = "/addEvent/toSaved/{eventId}")
    public ResponseEntity<?> addEventToSaved(
            @RequestHeader("Authorization") AuthenticationResponse authResponse,
            @PathVariable Long eventId) {

        return ResponseEntity.ok()
                .body(userService.saveEventToSaved(
                        jwtUtil.extractUserIdFromAuthResponse(authResponse),
                        eventId));
    }

    @DeleteMapping(path = "/removeEvent/fromSaved/{eventId}")
    public ResponseEntity<?> removeEventFromSaved(
            @RequestHeader("Authorization") AuthenticationResponse authResponse,
            @PathVariable Long eventId) {

        userService.removeEventFromSaved(
                jwtUtil.extractUserIdFromAuthResponse(authResponse),
                eventId);

        return ResponseEntity.ok().body("Event successfully removed from User saved ");
    }

    @DeleteMapping(path = "/removeEvent/fromOrder/{eventId}")
    public ResponseEntity<?> removeEventFromOrder(
            @RequestHeader("Authorization") AuthenticationResponse authResponse,
            @PathVariable Long eventId) {

        userService.removeEventFromOrder(
                jwtUtil.extractUserIdFromAuthResponse(authResponse),
                eventId);
        return ResponseEntity.ok()
                .body("Event successfully removed from User order ");
    }

    @GetMapping(path = "/allEvents/fromUserOrder")
    public ResponseEntity<?> getAllEventsFromUserOrder(
            @RequestHeader("Authorization") AuthenticationResponse authResponse) {
        return ResponseEntity.ok()
                .body(userService.getAllFromOrder(
                        jwtUtil.extractUserIdFromAuthResponse(authResponse)));
    }

    @GetMapping(path = "/allEvents/fromUserSaved")
    public ResponseEntity<?> getAllEventsFromUserSaved
            (@RequestHeader("Authorization") AuthenticationResponse authResponse) {
        return ResponseEntity.ok()
                .body(userService.getAllFromSaved(
                        jwtUtil.extractUserIdFromAuthResponse(authResponse)));
    }
}