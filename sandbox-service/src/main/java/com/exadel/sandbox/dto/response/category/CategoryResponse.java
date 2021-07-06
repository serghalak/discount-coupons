package com.exadel.sandbox.dto.response.category;

import com.exadel.sandbox.dto.response.tag.TagResponse;
import com.exadel.sandbox.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CategoryResponse extends BaseEntity {

    private String name;

    private String description;

    private Set<TagResponse> tags;

}