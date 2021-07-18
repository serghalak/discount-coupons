package com.exadel.sandbox.dto.response.statistics;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ViewedStatisticResponse {

    private long eventId;

    private String eventDescription;

    private String vendorName;

    private long count;

}
