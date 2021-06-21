package com.exadel.sandbox.controllers.vendor;

import com.exadel.sandbox.dto.response.vendor.VendorDetailsResponse;
import com.exadel.sandbox.dto.response.vendor.VendorResponse;
import com.exadel.sandbox.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
@CrossOrigin
@RequiredArgsConstructor
public class VendorController {
    private final VendorService service;

    @GetMapping
    public ResponseEntity<List<VendorResponse>> getAllByUserLocation() {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();

        return ResponseEntity.ok(service.findAllByUserLocation(email));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorDetailsResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }
}