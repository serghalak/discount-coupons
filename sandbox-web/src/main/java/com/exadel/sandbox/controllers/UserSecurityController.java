package com.exadel.sandbox.controllers;

import com.exadel.sandbox.dto.request.user.UserRequest;
import com.exadel.sandbox.dto.response.user.AuthenticationResponse;
import com.exadel.sandbox.security.utill.JwtUtil;
import com.exadel.sandbox.service.impl.UserSecurityServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//@AllArgsConstructor
//@RestController
public class UserSecurityController {

    //private final UserSecurityServiceImpl userService;
    //private final UserDetailsService userService;
//    private final AuthenticationManager authManager;
//
//    private final JwtUtil jwtTokenUtil;

//    @PostMapping("/authenticate")
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserRequest userRequest) throws BadCredentialsException {
//        try {
//            authManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword())
//            );
//
//        } catch (AuthenticationException e) {
//            throw new BadCredentialsException("Incorrect email or password");
//        }
//
//        final var userDetails = userService.loadUserByUsername(userRequest.getUsername());
//
//        final String jwt = jwtTokenUtil.generateToken(userDetails);
//
//        return ResponseEntity.ok(new AuthenticationResponse(jwt));
//    }
}
