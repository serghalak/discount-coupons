package com.exadel.sandbox.dto.request.category;

import com.exadel.sandbox.dto.request.tag.TagRequest;
import com.exadel.sandbox.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CategoryRequest extends BaseEntity {

    @NotBlank(message = "Category name is mandatory")
    @Size(min = 1, max = 255, message = "Category name should be from 1 to 255")
    private String name;

    @Size(max = 255, message = "Category description should not be more then 255")
    private String description;

    //@Size(min = 1, message = "Category has to have at least one tag")
    private Set<@Valid TagRequest> tags = new HashSet<>();

}
