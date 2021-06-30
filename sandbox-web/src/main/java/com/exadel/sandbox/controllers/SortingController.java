package com.exadel.sandbox.controllers;

import com.exadel.sandbox.security.utill.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class SortingController {

    private final JwtUtil jwtUtil;

    @GetMapping("/sortBy/{type}")
    public ResponseEntity<?> sortEventBy(@PathVariable String type){

        return null;
    }
}
