package com.exadel.sandbox.controllers.vendor;

import com.exadel.sandbox.model.vendorinfo.Vendor;
import com.exadel.sandbox.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
@CrossOrigin
@RequiredArgsConstructor
public class VendorController {
    private final VendorService service;

    @GetMapping
    public ResponseEntity<List<Vendor>> getAllByUserLocation() {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(service.findAllByUserLocation(email));
    }
}