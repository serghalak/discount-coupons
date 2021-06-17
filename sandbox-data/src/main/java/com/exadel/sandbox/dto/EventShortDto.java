package com.exadel.sandbox.dto;

import com.exadel.sandbox.model.location.Location;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Component
public class EventShortDto {
    private Long id;

    private String name;

    private int discount;

    private String shortDescription;

    private String vendor;

//    private BigDecimal price;


//    private String status;
//
//    private String online;

    private String country;

    private String city;

    private Set<Location> location;

    private String dateBegin;

    private String dateEnd;
//
//    private int quantityOfCoupons;
//
//    private int maxQuantityPerCustomer;
//
//    private String detailedDescription;
}
