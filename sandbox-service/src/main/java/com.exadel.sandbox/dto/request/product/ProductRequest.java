package com.exadel.sandbox.dto.request.product;

import com.exadel.sandbox.dto.request.vendor.VendorRequest;
import com.exadel.sandbox.dto.request.category.CategoryRequest;
import com.exadel.sandbox.model.BaseEntity;
import com.exadel.sandbox.model.vendorinfo.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductRequest extends BaseEntity {

    private String name;

    private String description;

    private String link;

    private CategoryRequest category;

    private VendorRequest vendor;

    private Set<Event> events;

}
