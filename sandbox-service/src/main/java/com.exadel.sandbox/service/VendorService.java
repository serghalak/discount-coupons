package com.exadel.sandbox.service;

import com.exadel.sandbox.dto.pagelist.PageList;
import com.exadel.sandbox.dto.response.vendor.VendorShortResponse;

public interface VendorService {

    PageList<VendorShortResponse> findAll(Integer pageNumber, Integer pageSize);

}
