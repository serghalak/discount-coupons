package com.exadel.sandbox.dto.response.filter;

import com.exadel.sandbox.dto.response.vendor.VendorShortResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorFilterResponse extends VendorShortResponse {

    private boolean isChecked;
}
