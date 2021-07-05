package com.exadel.sandbox.dto.request.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorLocationRequest {

    @Min(-90)
    @Max(90)
    private double latitude;

    @Min(-180)
    @Max(180)
    private double longitude;

    @NotNull
    @Size(min = 4, max = 80)
    private String street;

    @NotNull
    @Size(min = 1, max = 20)
    private String number;

}
