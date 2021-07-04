package com.exadel.sandbox.repository.vendor;

import com.exadel.sandbox.model.vendorinfo.Vendor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface VendorRepository extends JpaRepository<Vendor, Long>, VendorRepositoryCustom {

    @Query(value = "SELECT count(e.id) AS eventcount,\n" +
            "       v.id,\n" +
            "       v.name\n" +
            "FROM vendor v\n" +
            "         JOIN event e ON v.id = e.vendor_id\n" +
            "GROUP BY v.id, v.name", nativeQuery = true)
    Page<VendorProjection> findAllWithEventsCount(Pageable pageable);

}
