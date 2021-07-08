package com.exadel.sandbox.controllers;

import com.exadel.sandbox.dto.response.user.AuthenticationResponse;
import com.exadel.sandbox.security.utill.JwtUtil;
import com.exadel.sandbox.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class OrderController {

    private final OrderService orderService;
    private final JwtUtil jwtUtil;

    @PostMapping(path = "/addEvent/toOrder")
    public ResponseEntity<?> addEventToUserOrder(
            @RequestHeader("Authorization") AuthenticationResponse authResponse,
            @RequestParam(name = "eventId") Long eventId) {

        return ResponseEntity.ok()
                .body(orderService.saveEventToOrder(
                        jwtUtil.extractUserIdFromAuthResponse(authResponse),
                        eventId,
                        jwtUtil.extractEmailFromAuthResponse(authResponse)));
    }

    @DeleteMapping(path = "/removeEvent/fromOrder")
    public ResponseEntity<?> removeEventFromOrder(
            @RequestHeader("Authorization") AuthenticationResponse authResponse,
            @RequestParam(name = "eventId") Long eventId) {

        return ResponseEntity.ok()
                .body(orderService.removeEventFromOrder(
                        eventId,
                        jwtUtil.extractUserIdFromAuthResponse(authResponse)));
    }

    @GetMapping(path = "/allEvents/fromUserOrder")
    public ResponseEntity<?> getAllEventsFromUserOrder(
            @RequestHeader("Authorization") AuthenticationResponse authResponse,
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return ResponseEntity.ok()
                .body(orderService.getAllFromOrder(
                        jwtUtil.extractUserIdFromAuthResponse(authResponse), pageNumber, pageSize));
    }
}
