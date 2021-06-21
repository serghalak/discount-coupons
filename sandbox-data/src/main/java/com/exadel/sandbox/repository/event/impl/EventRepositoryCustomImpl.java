package com.exadel.sandbox.repository.event.impl;


import com.exadel.sandbox.model.vendorinfo.Event;
import com.exadel.sandbox.repository.event.EventRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EventRepositoryCustomImpl implements EventRepositoryCustom {

    private final EntityManager entityManager;


    @Override
    public List<Event> findAllByVendorId(Long vendorId) {
        return entityManager.createNativeQuery(
                "SELECT * FROM event\n" +
                        "    LEFT JOIN event_location ON event.id = event_location.event_id\n" +
                        "    LEFT JOIN location ON event_location.location_id = location.id\n" +
                        "    LEFT JOIN city ON location.city_id = city.id\n" +
                        "    LEFT JOIN country ON city.country_id = country.id\n" +
                        "    LEFT JOIN event_product ep ON event.id = ep.event_id\n" +
                        "    LEFT JOIN product p ON ep.product_id = p.id\n" +
                        "WHERE vendor_id = ?",
                Event.class
        )
                .setParameter(1, vendorId)
                .getResultList();
    }
}