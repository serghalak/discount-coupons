package com.exadel.sandbox.dto;

import com.exadel.sandbox.model.location.Location;
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
public class EventDetailedDto {

    private Long id;

    private String name;

    private String shortDescription;

    private String vendor;

    private BigDecimal price;

    private int discount;

    private String country;

    private String city;

    private Set<Location> location;

    private String dateBegin;

    private String dateEnd;

    private String detailedDescription;

}
