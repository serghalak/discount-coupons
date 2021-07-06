package com.exadel.sandbox.controllers;

import com.exadel.sandbox.security.utill.JwtUtil;
import com.exadel.sandbox.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<?> getUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok().body(userService.findByName(authentication.getName()));
    }
}