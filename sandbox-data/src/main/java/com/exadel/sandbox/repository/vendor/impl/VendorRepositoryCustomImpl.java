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
    public List<Vendor> findAllByUserLocation(String email) {
        return entityManager.createNativeQuery(
                "SELECT DISTINCT vendor.*\n" +
                        "FROM vendor\n" +
                        "         JOIN product p ON vendor.id = p.vendor_id\n" +
                        "         JOIN event_product ep ON p.id = ep.product_id\n" +
                        "         JOIN event e ON ep.event_id = e.id\n" +
                        "         LEFT JOIN event_location el ON e.id = el.event_id\n" +
                        "         LEFT JOIN location l ON el.location_id = l.id\n" +
                        "WHERE e.is_online = TRUE\n" +
                        "   OR l.city_id = (SELECT city_id\n" +
                        "                   FROM location\n" +
                        "                            JOIN user u ON location.id = u.location_id\n" +
                        "                   WHERE u.email = ?\n" +
                        "                   LIMIT 1)\n" +
                        "ORDER BY name",
                Vendor.class)
                .setParameter(1, email)
                .getResultList();
    }
}