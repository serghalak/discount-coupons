package com.exadel.sandbox.service;


import com.exadel.sandbox.dto.pagelist.PageList;
import com.exadel.sandbox.dto.response.category.CategoryShortResponse;
import com.exadel.sandbox.dto.response.event.CustomEventResponse;
import com.exadel.sandbox.dto.response.location.LocationShortResponse;
import com.exadel.sandbox.dto.response.vendor.VendorShortResponse;

import java.util.List;

public interface FavouriteService {

    List<LocationShortResponse> eventsLocationsFromSaved(final Long userId);

    List<VendorShortResponse> vendorsFromSaved(final Long userId);

    List<CategoryShortResponse> categoriesFromSaved(final Long userId);

    PageList<CustomEventResponse> getAllFromSaved(final Long userId, final Long cityId, final Integer pageNumber, final Integer pageSize);

    String removeEventFromSaved(final Long userId, final Long eventId);

    String saveEventToSaved(final Long userId, final Long eventId);
}
