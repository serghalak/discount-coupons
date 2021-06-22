package com.exadel.sandbox.controllers;

import com.exadel.sandbox.security.utill.JwtUtil;
import com.exadel.sandbox.service.DetailsUser;
import com.exadel.sandbox.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/current")
    ResponseEntity<?> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok().body(userService.findByName(authentication.getName()));
    }

    @PostMapping(path = "/addEvent/toOrder/{eventId}")
    ResponseEntity<?> addEventToUserOrder(@PathVariable Long eventId){
            Principal pr = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ResponseEntity.ok().body(userService.saveEventToOrder(eventId,1));
    }

    @PostMapping(path = "/addEvent/toSaved/{eventId}")
    ResponseEntity<?> addEventToSaved(@RequestHeader (name="Authorization") String token,
                                      @PathVariable Long eventId){

        return ResponseEntity.ok().body(null);
    }


}