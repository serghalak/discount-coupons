package com.exadel.sandbox.dto.response.vendor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorResponse {

    private Long id;

    private String name;

    private String description;

    private String phoneNumber;

    private String email;

}