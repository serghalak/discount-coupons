package com.exadel.sandbox.service;

import com.exadel.sandbox.service.vendor.dto.VendorDto;
import com.exadel.sandbox.service.vendor.dto.VendorFullDto;

import java.util.List;

public interface VendorService {

    List<VendorDto> findAllByUserLocation(String email);

    VendorFullDto findById(Long id);
}
