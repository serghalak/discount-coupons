package com.exadel.sandbox.service.vendor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorDto {
    private Long id;
    private String name;
    private String description;
    private String phoneNumber;
    private String email;
}
