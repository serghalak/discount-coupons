package com.exadel.sandbox.dto.response.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationFilterResponse {

    private Long id;

    private String name;

    private boolean isCountry;

    private boolean isChecked;
}
