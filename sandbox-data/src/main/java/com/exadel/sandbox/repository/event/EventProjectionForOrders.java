package com.exadel.sandbox.repository.event;

import com.exadel.sandbox.model.vendorinfo.Vendor;

import java.time.LocalDateTime;

public interface EventProjectionForOrders {
    Long getEventId();

    LocalDateTime getDateEvent();

    String getEventDescription();

    Vendor getVendor();

    Long getVendorId();

    String getVendorName();

    LocalDateTime getDateEnd();

    String getCityName();

    String getCountryName();

}
