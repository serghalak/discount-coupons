package com.exadel.sandbox.dto;

import lombok.*;
import org.springframework.stereotype.Component;

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

    private String country;

    private String city;

    private String dateBegin;

    private String dateEnd;

}
