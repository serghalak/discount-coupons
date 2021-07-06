package com.exadel.sandbox.dto.response.event;

import com.exadel.sandbox.dto.response.location.LocationResponse;
import com.exadel.sandbox.dto.response.tag.TagResponse;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Component
public class EventDetailsResponse {

    private Long id;

    private String description;

    private String fullDescription;

    private String vendorName;

    private Long vendorId;

    private String categoryName;

    private Long categoryId;

    private Set<TagResponse> tags;

    private Set<LocationResponse> locations;

    private LocalDateTime dateBegin;

    private LocalDateTime dateEnd;

}
