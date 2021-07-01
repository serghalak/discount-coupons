package com.exadel.sandbox.service.filter;

import com.exadel.sandbox.dto.request.filter.FilterRequest;
import com.exadel.sandbox.dto.response.filter.FilterResponse;

public interface FilterService {

    FilterResponse getFilterResponse(FilterRequest filterRequest);
}
