package com.exadel.sandbox.dto.request.product;

import com.exadel.sandbox.dto.request.vendor.VendorRequest;
import com.exadel.sandbox.dto.request.category.CategoryRequest;
import com.exadel.sandbox.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductRequest extends BaseEntity {

    private String name;

    private String description;

    private String link;

    private CategoryRequest categoryRequest;

    private VendorRequest vendorRequest;

}
