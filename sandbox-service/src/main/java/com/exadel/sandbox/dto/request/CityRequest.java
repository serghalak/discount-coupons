package com.exadel.sandbox.dto.request;

import com.exadel.sandbox.model.location.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityRequest {

    private String name;

    private Country country;
}
