package com.exadel.sandbox.repository.vendor.impl;

import com.exadel.sandbox.model.vendorinfo.Vendor;
import com.exadel.sandbox.repository.vendor.VendorRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class VendorRepositoryCustomImpl implements VendorRepositoryCustom {

    private final EntityManager entityManager;

    @Override
    public List<Vendor> findAllByUserLocation(String city) {
        return entityManager.createNativeQuery(
                "SELECT DISTINCT vendor.*\n" +
                        "FROM vendor\n" +
                        "         LEFT JOIN event e ON vendor.id = e.vendor_id\n" +
                        "         LEFT JOIN event_location el ON e.id = el.event_id\n" +
                        "         LEFT JOIN location l ON el.location_id = l.id\n" +
                        "         LEFT JOIN city c ON l.city_id = c.id\n" +
                        "WHERE e.is_online = TRUE\n" +
                        "   OR c.name = ? " +
                        "ORDER BY name",
                Vendor.class)
                .setParameter(1, city)
                .getResultList();
    }

}