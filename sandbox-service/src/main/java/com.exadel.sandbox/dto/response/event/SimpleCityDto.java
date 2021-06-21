package com.exadel.sandbox.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Component
public class SimpleCityDto {

    private Long id;

    private String name;

}
