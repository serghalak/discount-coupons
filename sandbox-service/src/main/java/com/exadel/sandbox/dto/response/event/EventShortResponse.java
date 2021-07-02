package com.exadel.sandbox.dto.response.event;

import com.exadel.sandbox.dto.response.location.LocationShortResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventShortResponse {

    private Long id;

    private String name;

    private String description;

    private int discount;

    private Set<LocationShortResponse> locationsResponse;
}