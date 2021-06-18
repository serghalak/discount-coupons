package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.response.vendor.VendorDetailsResponse;
import com.exadel.sandbox.dto.response.vendor.VendorResponse;
import com.exadel.sandbox.mappers.category.CategoryShortMapper;
import com.exadel.sandbox.mappers.event.EventShortMapper;
import com.exadel.sandbox.mappers.VendorMapper;
import com.exadel.sandbox.repository.CategoryRepository;
import com.exadel.sandbox.repository.EventRepository;
import com.exadel.sandbox.repository.vendor.VendorRepository;
import com.exadel.sandbox.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {
    private final VendorRepository repository;
    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryShortMapper categoryMapper;
    private final EventShortMapper eventMapper;
    private final VendorMapper vendorMapper;

    @Override
    public List<VendorResponse> findAllByUserLocation(String email) {
        return repository.findAllByUserLocation(email)
                .stream()
                .map(vendorMapper::vendorToVendorResponse)
                .collect(Collectors.toList());
    }

    @Override
    public VendorDetailsResponse findById(Long id) {
        var vendor = repository.findById(id)
                .map(vendorMapper::vendorToVendorResponse)
                .orElseThrow();
        var events = eventRepository.findAllByVendorId(id)
                .stream()
                .map(eventMapper::eventToEventShortResponse)
                .collect(Collectors.toList());
        var categories = categoryRepository.findAllByVendorId(id)
                .stream()
                .map(categoryMapper::categoryToCategoryShortResponse)
                .collect(Collectors.toList());
        return new VendorDetailsResponse(vendor, events, categories);
    }

}