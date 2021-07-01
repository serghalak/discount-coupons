package com.exadel.sandbox.dto.response.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomLocationResponse {

    private String countryName;

    private Map<String, Set<String>> addresses = new HashMap<>();

}
