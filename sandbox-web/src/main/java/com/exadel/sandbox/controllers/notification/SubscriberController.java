package com.exadel.sandbox.controllers.notification;

import com.exadel.sandbox.dto.request.filter.FilterRequest;
import com.exadel.sandbox.dto.request.notification.SubscriberRequest;
import com.exadel.sandbox.dto.response.filter.FilterResponse;
import com.exadel.sandbox.dto.response.notification.SubscriberResponse;
import com.exadel.sandbox.dto.response.user.AuthenticationResponse;
import com.exadel.sandbox.security.utill.JwtUtil;
import com.exadel.sandbox.service.notification.SubscriberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/")
@RestController
public class SubscriberController {

    private final JwtUtil jwtUtil;
    private final SubscriberService subscriberService;

    @PostMapping(produces = {"application/json"},
            consumes = {"application/json"},
            path = {"user/subscriprion"})
    public SubscriberResponse getVendorSubscriberList(@RequestBody SubscriberRequest subscriberRequest,
                                                      @RequestHeader("Authorization") AuthenticationResponse authenticationResponse) {

        Long userId = jwtUtil.extractUserIdFromAuthResponse(authenticationResponse);

        return subscriberService.getVendorSubscriberResponse(subscriberRequest, userId);

    }

}
