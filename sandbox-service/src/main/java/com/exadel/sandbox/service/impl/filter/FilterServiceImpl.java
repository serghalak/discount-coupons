package com.exadel.sandbox.service.impl.filter;

import com.exadel.sandbox.dto.request.filter.FilterRequest;
import com.exadel.sandbox.dto.response.category.CategoryResponse;
import com.exadel.sandbox.dto.response.filter.CategoryFilterResponse;
import com.exadel.sandbox.dto.response.filter.FilterResponse;
import com.exadel.sandbox.dto.response.filter.LocationFilterResponse;
import com.exadel.sandbox.dto.response.filter.VendorFilterResponse;
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

        //
//        List<LocationFilterResponse> locations = Stream.of(
//                    new LocationFilterResponse(1, "Belarus",true),
//                    new LocationFilterResponse(1, "Brest", false),
//                    new LocationFilterResponse(2, "Gomel",false))
//                .collect(Collectors.toList());
//        List<Category>categories=Stream.of(
//                    new CategoryFilterResponse(1,"Beauty",false),
//                    new CategoryFilterResponse(2,"Food",true))
//                .collect(Collectors.toList());
//        List<Vendor>vendors=Stream.of(
//                new VendorFilterResponse(3,"Foody",true),
//                new VendorFilterResponse(7,"adidas",false))
//                .collect(Collectors.toList());

//        List<Long>categories=Stream.of(1L,2L,3L,5L).collect(Collectors.toList());
//        List<Long>vendors=Stream.of(4L,6L).collect(Collectors.toList());
//        List<Long>tags=Stream.of(8L,10L).collect(Collectors.toList());

        String main = filterRequest.getMain();

        long locationId=filterRequest.getLocationId();
        boolean isCountry=filterRequest.getIsCountry();

        List<CategoryFilterResponse> allCategiriesByVendorFilter =
                getAllCategiriesByVendorFilter(filterRequest.getVendors());
        List<VendorFilterResponse> allVendorsByCategoryFilter =
                getAllVendorsByCategoryFilter(filterRequest.getCategories());
//        List<LocationFilterResponse>allLocationFiltersByCategoryFilter=
//                getAllLocationFiltersByCategoryFilter(filterRequest.getCategories());
        List<LocationFilterResponse>allLocationFiltersByVendorFilter=
                getAllLocationFiltersByVendorFilter(filterRequest.getVendors());

        return new FilterResponse(allLocationFiltersByVendorFilter,
                allCategiriesByVendorFilter,
                allVendorsByCategoryFilter,
                null);
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
