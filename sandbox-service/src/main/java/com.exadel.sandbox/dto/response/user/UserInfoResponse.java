package com.exadel.sandbox.dto.response.user;

import com.exadel.sandbox.dto.response.category.CategoryShortResponse;
import com.exadel.sandbox.dto.response.vendor.VendorShortResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Component
public class UserInfoResponse {

    private List<CategoryShortResponse> userCategory;

    private List<VendorShortResponse> userVendors;
}
