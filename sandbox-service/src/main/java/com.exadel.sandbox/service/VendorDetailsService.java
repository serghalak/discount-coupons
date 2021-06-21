package com.exadel.sandbox.service;

import com.exadel.sandbox.dto.response.vendor.VendorDetailsResponse;
import com.exadel.sandbox.dto.response.vendor.VendorShortResponse;

import java.util.List;

public interface VendorDetailsService {

    List<VendorShortResponse> findAllByUserLocation(Long userId);

    VendorDetailsResponse findById(Long id);
}
