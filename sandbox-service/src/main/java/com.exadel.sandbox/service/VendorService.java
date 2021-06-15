package com.exadel.sandbox.service;

import com.exadel.sandbox.model.vendorinfo.Vendor;

import java.util.List;

public interface VendorService {
    List<Vendor> findAllByUserLocation(String email);
}
