package com.exadel.sandbox.controllers.vendor;

import com.exadel.sandbox.dto.pagelist.PageList;
import com.exadel.sandbox.dto.request.vendor.VendorRequest;
import com.exadel.sandbox.dto.request.vendor.VendorUpdateRequest;
import com.exadel.sandbox.dto.response.vendor.CustomVendorResponse;
import com.exadel.sandbox.dto.response.vendor.VendorDetailsResponse;
import com.exadel.sandbox.dto.response.vendor.VendorShortResponse;
import com.exadel.sandbox.service.VendorDetailsService;
import com.exadel.sandbox.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

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

    @PostMapping
    public ResponseEntity<List<String>> create(@Valid @RequestBody VendorRequest request,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(getErrorMessages(bindingResult));
        }
        detailsService.create(request);

        return ResponseEntity.ok().build();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<List<String>> update(@RequestParam Long vendorId,
                                               @Valid @RequestBody VendorUpdateRequest request,
                                               BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(getErrorMessages(bindingResult));
        }

        detailsService.update(vendorId, request);

        return ResponseEntity.ok().build();
    }

    @NotNull
    private List<String> getErrorMessages(BindingResult bindingResult) {
        return bindingResult.getAllErrors().
                stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
    }
}