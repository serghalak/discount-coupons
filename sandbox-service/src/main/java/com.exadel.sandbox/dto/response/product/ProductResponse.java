package com.exadel.sandbox.dto.response.product;

import com.exadel.sandbox.dto.response.category.CategoryResponse;
import com.exadel.sandbox.dto.response.vendor.VendorResponse;
import com.exadel.sandbox.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductResponse extends BaseEntity {

    private String name;

    private String description;

    private String link;

    private CategoryResponse categoryResponse;

    private VendorResponse vendorResponse;

}