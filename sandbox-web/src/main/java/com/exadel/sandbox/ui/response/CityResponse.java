package com.exadel.sandbox.ui.response;

import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Component
@ToString
public class CityResponse implements Serializable {

    private Long id;

    private String name;

    private String countryName;

    private Long countryId;
}
