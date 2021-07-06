package com.exadel.sandbox.dto.request.vendor;

import com.exadel.sandbox.dto.request.location.VendorLocationUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorUpdateRequest {

//    @NotNull(message = "Id is mandatory")
//    private Long id;

    @NotNull(message = "Name is mandatory")
    @Size(min = 2, max = 80, message = "Name must be between 2 and 80 characters")
    private String name;

    @NotNull(message = "Name is mandatory")
    @Size(min = 10, max = 255, message = "Description must be between 10 and 255 characters")
    private String description;

    @NotNull(message = "Name is mandatory")
    @Size(min = 12, max = 12, message = "Phone number must contain 12 characters")
    private String phoneNumber;

    @Email(message = "Incorrect email")
    @NotNull(message = "Email is mandatory")
    private String email;

    private List<VendorLocationUpdateRequest> locationRequests;
}
