package com.exadel.sandbox.dto.request.category;

import com.exadel.sandbox.dto.request.tag.TagRequest;
import com.exadel.sandbox.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CategoryRequest extends BaseEntity {

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String description;

    private Set<TagRequest> tags;

}
