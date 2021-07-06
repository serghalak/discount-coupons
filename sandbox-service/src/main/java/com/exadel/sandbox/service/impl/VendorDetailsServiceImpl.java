package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.request.vendor.VendorRequest;
import com.exadel.sandbox.dto.response.filter.VendorFilterResponse;
import com.exadel.sandbox.dto.response.vendor.VendorDetailsResponse;
import com.exadel.sandbox.dto.response.vendor.VendorShortResponse;
import com.exadel.sandbox.mappers.vendor.VendorMapper;
import com.exadel.sandbox.mappers.vendor.VendorShortMapper;
import com.exadel.sandbox.model.location.City;
import com.exadel.sandbox.model.location.Location;
import com.exadel.sandbox.model.vendorinfo.Vendor;
import com.exadel.sandbox.repository.location_repository.CityRepository;
import com.exadel.sandbox.repository.location_repository.LocationRepository;
import com.exadel.sandbox.repository.vendor.VendorRepository;
import com.exadel.sandbox.service.CityService;
import com.exadel.sandbox.service.LocationService;
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
    private final LocationRepository locationRepository;
    private final CityService cityService;
    private final LocationService locationService;

    @Override
    public List<VendorShortResponse> findAllByUserLocation(Long userId) {
        var cityName = cityService.findCityNameByUserId(userId);
        return repository.findAllByUserLocation(cityName)
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
        var city = cityService.findById(cityId);
        checkVendorNameExisting(request);
        var location = getLocation(request, city);
        var savedLocation = locationRepository.save(location);
        var vendor = vendorMapper.vendorRequestToVendor(request);
        vendor.getLocations().add(savedLocation);
        repository.save(vendor);
    }

    @Override
    public void update(Long cityId, Long vendorId, Long locationId, VendorRequest request) {
        var city = cityService.findById(cityId);
        var vendorFromDB = repository.findById(vendorId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Not found vendor by id ,k. %d", vendorId)));
        checkVendorNameExisting(request);
        var updatedLocation = locationService.update(locationId, request, city);
        var vendor = vendorMapper.vendorRequestToVendor(request);
        vendor.getLocations().add(updatedLocation);
        vendor.setId(vendorFromDB.getId());
        repository.save(vendor);
    }

    private void checkVendorNameExisting(VendorRequest request) {
        if (repository.findByName(request.getName()).isPresent()) {
            throw new DuplicateNameException("Vendor already exists");
        }
    }

    public Location getLocation(VendorRequest request, City city) {
        return Location.builder().latitude(request.getLocationRequest().getLatitude())
                .longitude(request.getLocationRequest().getLongitude())
                .number(request.getPhoneNumber())
                .street(request.getLocationRequest().getStreet())
                .city(city).build();
    }
}