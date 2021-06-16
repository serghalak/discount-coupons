package com.exadel.sandbox.ui.response;


import com.exadel.sandbox.model.BaseEntity;
import com.exadel.sandbox.ui.request.CategoryRequest;
import com.exadel.sandbox.ui.request.VendorRequest;
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
