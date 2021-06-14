package com.exadel.sandbox.ui.response;

import com.exadel.sandbox.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CategoryResponse extends BaseEntity {

    private Long id;
    private String name;
    private String description;
}
