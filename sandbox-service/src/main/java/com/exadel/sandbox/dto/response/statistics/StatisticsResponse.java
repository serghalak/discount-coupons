package com.exadel.sandbox.dto.response.statistics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StatisticsResponse {

    private long eventId;

    private String eventDescription;

    private String vendorName;

    private long count;

}
