package com.exadel.sandbox.dto.request.vendor;

import com.exadel.sandbox.dto.request.location.VendorLocationUpdateRequest;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "locationRequests")
@Builder
public class VendorUpdateRequest {

    @NotNull(message = "Name is mandatory")
    @Size(min = 2, max = 80, message = "Name must be between 2 and 80 characters")
    private String name;

    @NotNull(message = "description is mandatory")
    @Size(min = 10, max = 255, message = "Description must be between 10 and 255 characters")
    private String description;

    @NotNull(message = "phoneNumber is mandatory")
    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$",
            message = "Incorrect phone number")
    private String phoneNumber;

    @Email(message = "Incorrect email")
    @NotNull(message = "Email is mandatory")
    private String email;

    @Size(min = 1, message = "Count of locations must be not less than 1")
    private Set<VendorLocationUpdateRequest> locationRequests;
}
