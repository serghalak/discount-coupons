package com.exadel.sandbox.service.impl.filter;

import com.exadel.sandbox.dto.request.filter.FilterRequest;
import com.exadel.sandbox.dto.response.category.CategoryResponse;
import com.exadel.sandbox.dto.response.filter.*;
import com.exadel.sandbox.model.vendorinfo.Category;
import com.exadel.sandbox.model.vendorinfo.Vendor;
import com.exadel.sandbox.service.CategoryService;
import com.exadel.sandbox.service.LocationService;
import com.exadel.sandbox.service.VendorDetailsService;
import com.exadel.sandbox.service.filter.FilterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@AllArgsConstructor
@Service
public class FilterServiceImpl implements FilterService {

    private CategoryService categoryService;
    private VendorDetailsService vendorService;
    private LocationService locationService;

    @Override
    public FilterResponse getFilterResponse(FilterRequest filterRequest) {

        switch (filterRequest.getMain()){
            case "location":
                return getFilterResponseMainLocation(filterRequest);
            case "categories":
                return getFilterResponseMainCategories(filterRequest);
            case "tags":
                return getFilterResponseMainTags(filterRequest);
            case "vendors":
                return getFilterResponseMainVendors(filterRequest);
            default:
                return getFilterResponseAll(filterRequest);
        }


    }

    private FilterResponse getFilterResponseAll(FilterRequest filterRequest){
        List<LocationFilterResponse> allLocationfilter=locationService.findAllLocationFilter();
        List<CategoryFilterResponse> allCategoriesFilter=categoryService.findAllCategoryFilter();
        List<TagFilterResponse>allTagsFilter=null;
        List<VendorFilterResponse>allVendorsFilter=vendorService.findAllVendorFilter();

        return new FilterResponse(allLocationfilter,allCategoriesFilter,null,allVendorsFilter);
    }

    private FilterResponse getFilterResponseMainTags(FilterRequest filterRequest){
        return new FilterResponse(null,null,null,null);
    }


    private FilterResponse getFilterResponseMainVendors(FilterRequest filterRequest){
        List<LocationFilterResponse>allLocationFiltersByVendorFilter=
                getAllLocationFiltersByVendorFilter(filterRequest.getVendors());
        List<CategoryFilterResponse> allCategiriesByVendorFilter =
                getAllCategiriesByVendorFilter(filterRequest.getVendors());

        return new FilterResponse(allLocationFiltersByVendorFilter,allCategiriesByVendorFilter,null,null);
    }

    private FilterResponse getFilterResponseMainLocation(FilterRequest filterRequest){

        List<CategoryFilterResponse> allCategiriesByLocationFilter =
                getAllCategiriesByLocationFilter(filterRequest.getLocationId(), filterRequest.getIsCountry());

        List<VendorFilterResponse> allVendorsByLocationFilter =
                getAllVendorsByLocationFilter(filterRequest.getLocationId(), filterRequest.getIsCountry());

        return new FilterResponse(null,allCategiriesByLocationFilter,null,allVendorsByLocationFilter);
    }

    private FilterResponse getFilterResponseMainCategories(FilterRequest filterRequest){

        List<LocationFilterResponse>allLocationFiltersByCategoryFilter=
                getAllLocationFiltersByCategoryFilter(filterRequest.getCategories());

        List<VendorFilterResponse> allVendorsByCategoryFilter =
                getAllVendorsByCategoryFilter(filterRequest.getCategories());
        return new FilterResponse(allLocationFiltersByCategoryFilter,null,null,allVendorsByCategoryFilter);
    }

    private List<LocationFilterResponse> getAllLocationFiltersByCategoryFilter(List<Long>ids){
        return locationService.findAllLocationFilterByCategoryFilter(ids);
    }

    private List<LocationFilterResponse> getAllLocationFiltersByVendorFilter(List<Long>ids){
        return locationService.findAllLocationFilterByVendorFilter(ids);
    }

    private List<CategoryFilterResponse>getAllCategiriesByLocationFilter(long locationId, boolean isCountry){
        return categoryService.findAllCategoryByLocationFilter(locationId,isCountry);
    }

    private List<CategoryFilterResponse>getAllCategiriesByVendorFilter(List<Long>ids){
        return categoryService.findAllCategoryByVendorFilter(ids);
    }

    private List<VendorFilterResponse>getAllVendorsByLocationFilter(long locationId, boolean isCountry){
        return vendorService.findAllVendorByLocationFilter(locationId,isCountry);
    }

    private List<VendorFilterResponse>getAllVendorsByCategoryFilter(List<Long>ids){
        return vendorService.findAllVendorByCategoryFilter(ids);
    }
}
