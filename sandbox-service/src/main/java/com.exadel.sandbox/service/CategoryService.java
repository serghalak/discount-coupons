package com.exadel.sandbox.service;

import com.exadel.sandbox.dto.request.category.CategoryRequest;
import com.exadel.sandbox.dto.response.category.CategoryResponse;
import com.exadel.sandbox.dto.response.category.CategoryShortResponse;
import com.exadel.sandbox.dto.response.filter.CategoryFilterResponse;
import com.exadel.sandbox.model.vendorinfo.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {

    void deleteCategoryById(Long categoryId);

    CategoryResponse updateCategory(Long categoryId, CategoryRequest categoryRequest);

    Set<CategoryResponse> listCategoriesByPartOfName(String categoryName);

    CategoryResponse findCategoryById(Long categoryId);

    CategoryResponse saveCategory(CategoryRequest categoryRequest);

    boolean isCategoryNameExists(String categoryName);

    Set<CategoryResponse> listCategories();

    List<CategoryShortResponse> findAllCategoriesByVendorId(Long vendorId);

    List<CategoryShortResponse>findAllByVendorId(Long vendorId);

    List<CategoryFilterResponse>findAllCategoryByLocationFilter(Long id, boolean isCountry);

    List<CategoryFilterResponse>findAllCategoryByVendorFilter(List<Long> ids);

}
