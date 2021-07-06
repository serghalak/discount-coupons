package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.request.vendor.VendorRequest;
import com.exadel.sandbox.dto.response.filter.VendorFilterResponse;
import com.exadel.sandbox.dto.response.vendor.VendorDetailsResponse;
import com.exadel.sandbox.dto.response.vendor.VendorShortResponse;
import com.exadel.sandbox.mappers.vendor.VendorMapper;
import com.exadel.sandbox.mappers.vendor.VendorShortMapper;
import com.exadel.sandbox.model.location.City;
import com.exadel.sandbox.model.location.Location;
import com.exadel.sandbox.repository.location_repository.CityRepository;
import com.exadel.sandbox.repository.location_repository.LocationRepository;
import com.exadel.sandbox.repository.vendor.VendorRepository;
import com.exadel.sandbox.service.VendorDetailsService;
import com.exadel.sandbox.service.exceptions.DuplicateNameException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorDetailsServiceImpl implements VendorDetailsService {
    private final VendorRepository repository;
    private final VendorMapper vendorMapper;
    private final VendorShortMapper vendorShortMapper;
    private final CityRepository cityRepository;
    private final LocationRepository locationRepository;

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
    public VendorDetailsResponse findByIdWithLocations(Long id) {
        return repository.findByIdFetchLocations(id)
                .map(vendorMapper::vendorToVendorDetailsResponse)
                .orElseThrow(() -> new EntityNotFoundException((String.format("Vendor with id %d not found", id))));
    }


    @Override
    public List<VendorFilterResponse> findAllVendorByLocationFilter(Long id, boolean isCountry) {
        return repository.findAllByLocationFilterId(id, isCountry).stream()
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
    public List<VendorFilterResponse> findAllVendorFilter() {
        return repository.findAll().stream()
                .map(vendorMapper::vendorToVendorFilterResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void create(Long cityId, VendorRequest request) {
        var city = cityRepository.findById(cityId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Not found city by id%d", cityId)));
        checkVendorNameExisting(request);
        var location = getLocation(request, city);
        var savedLocation = locationRepository.save(location);
        var vendor = vendorMapper.vendorRequestToVendor(request);
        vendor.getLocations().add(savedLocation);
        repository.save(vendor);
    }

    private void checkVendorNameExisting(VendorRequest request) {
        if (repository.findByName(request.getName()).isPresent()) {
            throw new DuplicateNameException("Vendor already exists");
        }
    }

    private Location getLocation(VendorRequest request, City city) {
        return Location.builder().latitude(request.getLocationRequest().getLatitude())
                .longitude(request.getLocationRequest().getLongitude())
                .number(request.getPhoneNumber())
                .street(request.getLocationRequest().getStreet())
                .city(city).build();
    }
}