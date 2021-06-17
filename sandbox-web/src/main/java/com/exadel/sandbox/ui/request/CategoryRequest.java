package com.exadel.sandbox.ui.request;

import com.exadel.sandbox.model.BaseEntity;
import lombok.*;

import javax.persistence.UniqueConstraint;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CategoryRequest extends BaseEntity {

    private String name;
    private String description;

}
