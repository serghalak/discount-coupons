package com.exadel.sandbox.dto.response.product;

import com.exadel.sandbox.dto.response.category.CategoryResponse;
import com.exadel.sandbox.dto.response.event.EventResponse;
import com.exadel.sandbox.dto.response.vendor.VendorResponse;
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
public class ProductResponse extends BaseEntity {

    private String name;

    private String description;

    private String link;

    private CategoryResponse category;

    private VendorResponse vendor;

    private Set<EventResponse> events;

}