package com.exadel.sandbox.controllers.vendor;

import com.exadel.sandbox.dto.pagelist.PageList;
import com.exadel.sandbox.dto.response.vendor.VendorShortResponse;
import com.exadel.sandbox.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendors")
@CrossOrigin
@RequiredArgsConstructor
public class VendorController {

    private final VendorService service;

    @GetMapping
    public PageList<VendorShortResponse> getAll(
            @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", required = false) Integer pageSize) {
        return service.findAll(pageNumber, pageSize);

    }
}