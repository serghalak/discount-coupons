package com.exadel.sandbox.dto.request.event;

import com.exadel.sandbox.model.vendorinfo.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {

    private String name;

    private String description;

    private String fullDescription;

    private String email;

    private int totalCount;

    private boolean isOnline;

    private String phoneNumber;

    private int limitation;

    private int discount;

    private BigDecimal price;

    private LocalDateTime dateBegin;

    private LocalDateTime dateEnd;

    private Set<Long> locationsId;

    private Long categoryId;

    private Set<Long> tagsId;

    private Status status;


}
