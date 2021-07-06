package com.exadel.sandbox.controllers.vendor;

import com.exadel.sandbox.dto.pagelist.PageList;
import com.exadel.sandbox.dto.response.vendor.CustomVendorResponse;
import com.exadel.sandbox.dto.response.vendor.VendorDetailsResponse;
import com.exadel.sandbox.dto.response.vendor.VendorShortResponse;
import com.exadel.sandbox.service.VendorDetailsService;
import com.exadel.sandbox.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendors")
@CrossOrigin
@RequiredArgsConstructor
public class VendorController {

    private final VendorService service;
    private final VendorDetailsService detailsService;

    @GetMapping
    public PageList<VendorShortResponse> getAll(
            @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", required = false) Integer pageSize) {
        return service.findAll(pageNumber, pageSize);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorDetailsResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(detailsService.findByIdWithLocations(id));
    }

    @GetMapping("/all")
    public PageList<CustomVendorResponse> count(
            @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", required = false) Integer pageSize) {
        return service.findAllWithEventsCount(pageNumber, pageSize);
    }
}