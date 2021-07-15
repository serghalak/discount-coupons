package com.exadel.sandbox.repository.statistics;

public interface OrderStatisticsProjection {

    Long getEventId();
    String getEventDescription();
    String getVendorName();
}
