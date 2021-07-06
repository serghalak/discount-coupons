package com.exadel.sandbox.dto.request.event;

import com.exadel.sandbox.model.vendorinfo.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {

    @NotBlank(message = "The 'name' cannot be empty")
    private String name;

    @NotBlank(message = "The 'description' cannot be empty")
    private String description;

    private String fullDescription;

    @Email(message = "email must have right format")
    private String email;

    @Min(1)
    private int totalCount;

    private boolean isOnline;

    private String phoneNumber;

    private int limitation;

    private int discount;

    private BigDecimal price;

    private LocalDateTime dateBegin;

    private LocalDateTime dateEnd;

    private Set<Long> locationsId;

    @NotNull(message = "The 'categoryId' cannot be empty")
    private Long categoryId;

    private Set<Long> tagsId;

    @NotNull(message = "The 'status' cannot be empty")
    private Status status;


}
