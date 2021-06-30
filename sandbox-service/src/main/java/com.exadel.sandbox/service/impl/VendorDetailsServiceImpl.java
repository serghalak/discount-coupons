package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.response.filter.CategoryFilterResponse;
import com.exadel.sandbox.dto.response.filter.VendorFilterResponse;
import com.exadel.sandbox.dto.response.vendor.VendorDetailsResponse;
import com.exadel.sandbox.dto.response.vendor.VendorShortResponse;
import com.exadel.sandbox.mappers.category.CategoryShortMapper;
import com.exadel.sandbox.mappers.event.EventShortMapper;
import com.exadel.sandbox.mappers.vendor.VendorMapper;
import com.exadel.sandbox.mappers.vendor.VendorShortMapper;
import com.exadel.sandbox.repository.category.CategoryRepository;
import com.exadel.sandbox.repository.event.EventRepository;
import com.exadel.sandbox.repository.location_repository.CityRepository;
import com.exadel.sandbox.repository.vendor.VendorRepository;
import com.exadel.sandbox.service.VendorDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorDetailsServiceImpl implements VendorDetailsService {
    private final VendorRepository repository;
    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryShortMapper categoryMapper;
    private final EventShortMapper eventMapper;
    private final VendorMapper vendorMapper;
    private final VendorShortMapper vendorShortMapper;
    private final CityRepository cityRepository;

    @Override
    public List<VendorShortResponse> findAllByUserLocation(Long userId) {
        var city = cityRepository.findCityNameByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("City with userId %d not found", userId)));
        return repository.findAllByUserLocation(city)
                .stream()
                .map(vendorShortMapper::vendorToVendorShortResponse)
                .collect(Collectors.toList());
    }

    @Override
    public VendorDetailsResponse findById(Long id) {
        var vendor = repository.findById(id)
                .map(vendorMapper::vendorToVendorResponse)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Vendor with id %d not found", id)));
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

    @Override
    public List<VendorFilterResponse> findAllVendorByLocationFilter(Long id, boolean isCountry) {
        return repository.findAllByLocationFilterId(id,isCountry).stream()
                .map(vendorMapper::vendorToVendorFilterResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<VendorFilterResponse> findAllVendorByCategoryFilter(List<Long> ids) {
        return repository.findAllByCategoryFilterIds(ids).stream()
                .map(vendorMapper::vendorToVendorFilterResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<VendorFilterResponse>findAllVendorFilter(){
        return repository.findAll().stream()
                .map(vendorMapper::vendorToVendorFilterResponse)
                .collect(Collectors.toList());
    }
}