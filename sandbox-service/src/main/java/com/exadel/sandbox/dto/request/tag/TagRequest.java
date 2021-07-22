package com.exadel.sandbox.dto.request.tag;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagRequest {

    private Long id;

    @NotBlank(message = "Tag name is mandatory")
    @Size(min = 1, max = 100, message = "Tag name should be from 1 to 100")
    private String name;
}
