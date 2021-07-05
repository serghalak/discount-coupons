package com.exadel.sandbox.dto.request.vendor;

import com.exadel.sandbox.dto.request.location.VendorLocationRequest;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorRequest {

    @NotNull
    @Size(min = 2, max = 80)
    private String name;

    @NotNull
    @Size(min = 10, max = 255)
    private String description;

    @NotNull
    @Size(min = 12,max = 12)
    private String phoneNumber;

    @Email
    @NotNull
    private String email;

    @NotNull
    private VendorLocationRequest locationRequest;

}
