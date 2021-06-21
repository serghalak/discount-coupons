package com.exadel.sandbox.repository.vendor;

import com.exadel.sandbox.model.vendorinfo.Vendor;

import java.util.List;

public interface VendorRepositoryCustom {

    List<Vendor> findAllByUserLocation(String city);

}
