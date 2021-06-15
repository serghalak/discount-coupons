package com.exadel.sandbox.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Component
public class EventDto {
    private Long id;

    private String name;

    private String shortDescription;

    private String vendor;

    private BigDecimal price;

    private int discount;

    private String status;

    private String online;

    private String country;

    private String city;

    private String street;

    private String streetNumber;

    private double latitude;

    private double longitude;

    private LocalDate dateBegin;

    private LocalDate dateEnd;

    private int quantityOfCoupons;

    private int maxQuantityPerCustomer;

    private String detailedDescription;

}
