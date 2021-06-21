package com.exadel.sandbox.service;

import com.exadel.sandbox.dto.response.vendor.VendorDetailsResponse;
import com.exadel.sandbox.dto.response.vendor.VendorResponse;

import java.util.List;

public interface VendorService {

    List<VendorResponse> findAllByUserLocation(String email);

    VendorDetailsResponse findById(Long id);
}
