package com.exadel.sandbox.controllers.notification;

import com.exadel.sandbox.dto.request.notification.SubscriberRequest;
import com.exadel.sandbox.dto.response.notification.SubscriberResponse;
import com.exadel.sandbox.dto.response.user.AuthenticationResponse;
import com.exadel.sandbox.model.notification.SubscriberEnum;
import com.exadel.sandbox.security.utill.JwtUtil;
import com.exadel.sandbox.service.notification.SubscriberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            path = {"user/subscription"})
    public boolean setSubscription(@RequestBody SubscriberRequest subscriberRequest,
                                   @RequestHeader("Authorization") AuthenticationResponse authenticationResponse) {

        Long userId = jwtUtil.extractUserIdFromAuthResponse(authenticationResponse);

        return subscriberService.saveSubscription(subscriberRequest, userId);

    }

    @GetMapping(produces = {"application/json"},
            path = {"user/subscription/vendor"})
    public List<SubscriberResponse> getAllVendorsSubscriberResponse(
            @RequestHeader("Authorization") AuthenticationResponse authenticationResponse) {

        Long userId = jwtUtil.extractUserIdFromAuthResponse(authenticationResponse);

        return subscriberService.getAllVendorSubscriber(userId, SubscriberEnum.VENDOR);

    }

    @GetMapping(produces = {"application/json"},
            path = {"user/subscription/category"})
    public List<SubscriberResponse> getAllCategoriesSubscriberResponse(
            @RequestHeader("Authorization") AuthenticationResponse authenticationResponse) {

        Long userId = jwtUtil.extractUserIdFromAuthResponse(authenticationResponse);

        return subscriberService.getAllCategorySubscriber(userId, SubscriberEnum.CATEGORY);

    }

    @GetMapping(produces = {"application/json"},
            path = {"user/subscription/tag"})
    public List<SubscriberResponse> getAllTagsSubscriberResponse(
            @RequestHeader("Authorization") AuthenticationResponse authenticationResponse) {

        Long userId = jwtUtil.extractUserIdFromAuthResponse(authenticationResponse);

        return subscriberService.getAllTagSubscriber(userId, SubscriberEnum.TAG);
    }

}
