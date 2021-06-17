package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.repository.CategoryRepository;
import com.exadel.sandbox.repository.EventRepository;
import com.exadel.sandbox.repository.vendor.VendorRepository;
import com.exadel.sandbox.service.VendorService;
import com.exadel.sandbox.service.vendor.dto.VendorDto;
import com.exadel.sandbox.service.vendor.dto.VendorFullDto;
import com.exadel.sandbox.service.vendor.mappers.CategoryMapper;
import com.exadel.sandbox.service.vendor.mappers.EventMapper;
import com.exadel.sandbox.service.vendor.mappers.VendorMapper;
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
    private final CategoryMapper categoryMapper;
    private final EventMapper eventMapper;
    private final VendorMapper vendorMapper;

    @Override
    public List<VendorDto> findAllByUserLocation(String email) {
        return repository.findAllByUserLocation(email)
                .stream()
                .map(vendorMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public VendorFullDto findById(Long id) {
        var vendor = repository.findById(id).map(vendorMapper::toDto).orElseThrow();
        var events = eventRepository.findAllByVendorId(id)
                .stream()
                .map(eventMapper::toDto)
                .collect(Collectors.toList());
        var categories = categoryRepository.findAllByVendorId(id)
                .stream()
                .map(categoryMapper::categoryToCategoryDto)
                .collect(Collectors.toList());
        return new VendorFullDto(vendor,  events, categories);
    }
}
