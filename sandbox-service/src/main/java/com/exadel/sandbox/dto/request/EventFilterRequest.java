package com.exadel.sandbox.dto.request;

import com.exadel.sandbox.model.vendorinfo.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventFilterRequest {

    private Long locationId = 0L;

    private boolean isCity;

    private Set<Long> categoriesIdSet = new HashSet<>();

    private Set<Long> tagsIdSet = new HashSet<>();

    private Set<Long> vendorsIdSet = new HashSet<>();

    private Status status = Status.ACTIVE;

    private SortingType sortingType = SortingType.HOTEST;

    public enum SortingType {
        NEWEST, HOTEST, POPULAR
    }


}
