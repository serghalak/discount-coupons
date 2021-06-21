package com.exadel.sandbox.repository.event;

import com.exadel.sandbox.model.vendorinfo.Event;

import java.util.List;

public interface EventRepositoryCustom {

    List<Event> findAllByVendorId(Long vendorId);

}
