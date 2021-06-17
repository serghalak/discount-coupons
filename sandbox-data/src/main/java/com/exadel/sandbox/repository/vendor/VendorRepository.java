package com.exadel.sandbox.repository.vendor;

import com.exadel.sandbox.model.vendorinfo.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long>, VendorRepositoryCustom {
}
