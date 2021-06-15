package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.model.vendorinfo.Vendor;
import com.exadel.sandbox.repository.vendor.VendorRepository;
import com.exadel.sandbox.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {
    private final VendorRepository repository;

    @Override
    public List<Vendor> findAllByUserLocation(String email) {
        return repository.findAllByUserLocation(email);
    }
}
