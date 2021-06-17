package com.exadel.sandbox.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Component
public class CityDto implements Serializable {

    private Long id;

    private String name;

    private String countryName;

    private Long countryId;
}
