package com.exadel.sandbox.controllers;

import com.exadel.sandbox.dto.response.user.AuthenticationResponse;
import com.exadel.sandbox.security.utill.JwtUtil;
import com.exadel.sandbox.service.FavouriteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class FavouriteController {

    private final FavouriteService favouriteService;
    private final JwtUtil jwtUtil;

    @GetMapping(path = "/allEvents/fromUserSaved")
    public ResponseEntity<?> getAllEventsFromUserSaved
            (@RequestHeader("Authorization") AuthenticationResponse authResponse) {
        return ResponseEntity.ok()
                .body(favouriteService.getAllFromSaved(
                        jwtUtil.extractUserIdFromAuthResponse(authResponse)));
    }

}
