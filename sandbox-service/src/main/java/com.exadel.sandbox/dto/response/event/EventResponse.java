package com.exadel.sandbox.dto.response.event;

import com.exadel.sandbox.dto.response.location.LocationShortResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventResponse {

    private Long id;

    private String shortDescription;

    private String fullDescription;

    private String vendorName;

    private Long vendorId;

    private Set<LocationShortResponse> locations;
    
    private LocalDateTime dateBegin;

    private LocalDateTime dateBegin;

    private LocalDateTime dateEnd;

}
