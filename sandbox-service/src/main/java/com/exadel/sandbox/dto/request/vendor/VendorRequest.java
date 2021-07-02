package com.exadel.sandbox.dto.request.vendor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorRequest {

    private String name;

    private String description;

    private String phoneNumber;

    private String email;

}
