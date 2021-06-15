package com.exadel.sandbox.dto;

import com.exadel.sandbox.model.location.City;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Component
public class CountryDto {

    private String name;

    private Set<City> cities;
}
