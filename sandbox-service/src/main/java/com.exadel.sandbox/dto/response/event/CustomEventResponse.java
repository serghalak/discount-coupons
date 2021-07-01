package com.exadel.sandbox.dto.response.event;

import com.exadel.sandbox.dto.response.location.CustomLocationResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomEventResponse {

    private Long id;

    private String shortDescription;

    private String vendorName;

    private Long vendorId;

    private String categoryName;

    private Long categoryId;

    private CustomLocationResponse locations;

    private LocalDateTime dateBegin;

    private LocalDateTime dateEnd;

}