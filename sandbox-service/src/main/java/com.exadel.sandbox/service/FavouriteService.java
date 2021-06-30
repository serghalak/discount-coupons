package com.exadel.sandbox.service;


import com.exadel.sandbox.dto.response.category.CategoryShortResponse;
import com.exadel.sandbox.dto.response.event.EventResponse;
import com.exadel.sandbox.dto.response.location.LocationShortResponse;
import com.exadel.sandbox.dto.response.vendor.VendorShortResponse;

import java.util.List;

public interface FavouriteService {

    List<LocationShortResponse> eventsLocationsFromSaved(final Long userId);

    List<VendorShortResponse> vendorsFromSaved(final Long userId);

    List<CategoryShortResponse> categoriesFromSaved(final Long userId);

    List<EventResponse> getAllFromSaved(final Long userId);
}
