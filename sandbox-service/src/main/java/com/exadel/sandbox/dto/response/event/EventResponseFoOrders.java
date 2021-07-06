package com.exadel.sandbox.dto.response.event;

import com.exadel.sandbox.dto.response.location.LocationShortResponse;
import com.exadel.sandbox.dto.response.vendor.VendorShortResponse;
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
public class EventResponseFoOrders {

    private Long id;

    private LocalDateTime gettingDate;

    private String description;

    private VendorShortResponse vendorShortResponse;

    private LocalDateTime dateEnd;

    private Set<LocationShortResponse> locations;


}
