package com.exadel.sandbox.service.filter.impl;

import com.exadel.sandbox.dto.request.filter.FilterRequest;
import com.exadel.sandbox.dto.response.filter.*;
import com.exadel.sandbox.service.CategoryService;
import com.exadel.sandbox.service.LocationService;
import com.exadel.sandbox.service.TagService;
import com.exadel.sandbox.service.VendorDetailsService;
import com.exadel.sandbox.service.filter.FilterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class FilterServiceImpl implements FilterService {

    private CategoryService categoryService;
    private VendorDetailsService vendorService;
    private LocationService locationService;
    private TagService tagService;

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
        List<LocationFilterResponse> allLocationFilter=locationService.findAllLocationFilter();
        List<CategoryFilterResponse> allCategoriesFilter=categoryService.findAllCategoryFilter();
        List<TagFilterResponse>allTagsFilter=tagService.findAllTagFilter();
        List<VendorFilterResponse>allVendorsFilter=vendorService.findAllVendorFilter();

        return new FilterResponse(allLocationFilter,allCategoriesFilter,allTagsFilter,allVendorsFilter);
    }

    private FilterResponse getFilterResponseMainLocation(FilterRequest filterRequest){

        List<CategoryFilterResponse> allCategiriesByLocationFilter =
                getAllCategiriesByLocationFilter(filterRequest.getLocationId(), filterRequest.getIsCountry());

        List<TagFilterResponse>allTagsByCategoryFilter=
                getAllTagsByCategoryFilterResponse(allCategiriesByLocationFilter);

        List<VendorFilterResponse> allVendorsByLocationFilter =
                getAllVendorsByLocationFilter(filterRequest.getLocationId(), filterRequest.getIsCountry());

        return new FilterResponse(null,allCategiriesByLocationFilter,allTagsByCategoryFilter,allVendorsByLocationFilter);
    }

    private FilterResponse getFilterResponseMainCategories(FilterRequest filterRequest){

        List<TagFilterResponse>allTagsByCategoryFilter=
                getAllTagsByCategoryFilter(filterRequest.getCategories());

        List<VendorFilterResponse> allVendorsByCategoryFilter =
                getAllVendorsByCategoryFilter(filterRequest.getCategories());

        return new FilterResponse(null,null,allTagsByCategoryFilter,allVendorsByCategoryFilter);
    }

    private FilterResponse getFilterResponseMainTags(FilterRequest filterRequest){

        return new FilterResponse(null,null,null,null);
    }


    private FilterResponse getFilterResponseMainVendors(FilterRequest filterRequest){
//        List<LocationFilterResponse>allLocationFiltersByVendorFilter=
//                getAllLocationFiltersByVendorFilter(filterRequest.getVendors());
//        List<CategoryFilterResponse> allCategiriesByVendorFilter =
//                getAllCategiriesByVendorFilter(filterRequest.getVendors());

        return new FilterResponse(null,null,null,null);
    }



    private List<TagFilterResponse>getAllTagsByCategoryFilter(List<Long>ids){

        if(ids.isEmpty() || ids.size()==0){
            return null;
        }else{
            return tagService.findAllTagsByCategoryFilter(ids);
        }
    }


    private List<TagFilterResponse>getAllTagsByCategoryFilterResponse(
            List<CategoryFilterResponse>categoryFilterResponses){

        if(categoryFilterResponses.isEmpty()){
            return null;
        }else {
            List<Long> ids = categoryFilterResponses.stream()
                    .map(CategoryFilterResponse::getId)
                    .collect(Collectors.toList());
            return tagService.findAllTagsByCategoryFilter(ids);
        }

    }

//    private List<LocationFilterResponse> getAllLocationFiltersByCategoryFilter(List<Long>ids){
//        return locationService.findAllLocationFilterByCategoryFilter(ids);
//    }

//    private List<LocationFilterResponse> getAllLocationFiltersByVendorFilter(List<Long>ids){
//        return locationService.findAllLocationFilterByVendorFilter(ids);
//    }

    private List<CategoryFilterResponse>getAllCategiriesByLocationFilter(long locationId, boolean isCountry){
        return categoryService.findAllCategoryByLocationFilter(locationId,isCountry);
    }

//    private List<CategoryFilterResponse>getAllCategiriesByVendorFilter(List<Long>ids){
//        return categoryService.findAllCategoryByVendorFilter(ids);
//    }

    private List<VendorFilterResponse>getAllVendorsByLocationFilter(long locationId, boolean isCountry){
        return vendorService.findAllVendorByLocationFilter(locationId,isCountry);
    }

    private List<VendorFilterResponse>getAllVendorsByCategoryFilter(List<Long>ids){
        return vendorService.findAllVendorByCategoryFilter(ids);
    }
}
