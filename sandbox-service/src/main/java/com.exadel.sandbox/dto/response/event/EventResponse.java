package com.exadel.sandbox.dto.response.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventResponse {

    private Long id;

    private String name;

    private int discount;

    private String shortDescription;

    private String vendor;

    private String country;

    private String city;

    private String dateBegin;

    private String dateEnd;

}