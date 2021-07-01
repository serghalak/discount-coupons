package com.exadel.sandbox.dto.response.filter;

import com.exadel.sandbox.dto.response.vendor.VendorShortResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class VendorFilterResponse extends VendorShortResponse {

    private boolean isChecked;
}
