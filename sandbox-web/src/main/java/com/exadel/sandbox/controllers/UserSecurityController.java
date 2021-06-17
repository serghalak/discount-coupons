package com.exadel.sandbox.controllers;

import com.exadel.sandbox.security.utill.JwtUtil;
import com.exadel.sandbox.service.impl.UserSecurityServiceImpl;
import com.exadel.sandbox.ui.request.UserRequest;
import com.exadel.sandbox.ui.response.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserSecurityController {

    @Autowired
    private UserSecurityServiceImpl userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtTokenUtil;

       @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserRequest userRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect email or password");
        }
        final UserDetails userDetails = userService.loadUserByUsername(userRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
