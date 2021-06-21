package com.exadel.sandbox.controllers.vendor;

import com.exadel.sandbox.dto.response.user.AuthenticationResponse;
import com.exadel.sandbox.dto.response.vendor.VendorDetailsResponse;
import com.exadel.sandbox.dto.response.vendor.VendorShortResponse;
import com.exadel.sandbox.security.utill.JwtUtil;
import com.exadel.sandbox.service.VendorDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
@CrossOrigin
@RequiredArgsConstructor
public class VendorController {
    private final VendorDetailsService service;

    private final JwtUtil jwtUtil;

    @GetMapping
    public ResponseEntity<List<VendorShortResponse>> getAllByUserLocation(
            @RequestHeader("Authorization") AuthenticationResponse authenticationResponse
    ) {

        Long userId = Long.parseLong(getIdFromHeadersJwt(authenticationResponse));
        return ResponseEntity.ok(service.findAllByUserLocation(userId));

    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorDetailsResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    private String getIdFromHeadersJwt(AuthenticationResponse authenticationResponse) {
        return jwtUtil.extractUserId(authenticationResponse.getJwt().substring(7));
    }
}