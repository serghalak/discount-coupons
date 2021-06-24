package com.exadel.sandbox.dto.response.event;

import com.exadel.sandbox.dto.response.location.LocationShortResponse;
import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Component
public class EventDetailsResponse {

    private Long id;

    private String name;

    private String shortDescription;

    private String detailedDescription;

    private String vendorName;

    private Long vendorId;

    private BigDecimal price;

    private int discount;

    private Set<LocationShortResponse> locations;

    private String dateBegin;

    private String dateEnd;


}
