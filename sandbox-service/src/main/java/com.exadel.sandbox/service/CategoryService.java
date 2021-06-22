package com.exadel.sandbox.service;

import com.exadel.sandbox.dto.pagelist.CategoryPagedList;
import com.exadel.sandbox.dto.request.category.CategoryRequest;
import com.exadel.sandbox.dto.response.category.CategoryResponse;
import com.exadel.sandbox.model.vendorinfo.Category;
import org.springframework.data.domain.PageRequest;

import java.util.Set;

public interface CategoryService {

    void deleteCategoryById(Long categoryId);

    CategoryResponse updateCategory(Long categoryId, CategoryRequest categoryRequest);

    CategoryPagedList listCategoriesByPartOfName(String categoryName, int pageNumber, int pageSize);

    CategoryResponse findCategoryById(Long categoryId);

    //CategoryPagedList listCategories(PageRequest pageRequest);

    CategoryResponse saveCategory(CategoryRequest categoryRequest);

    boolean isCategoryNameExists(String categoryName);

    CategoryPagedList listCategories(int pageNumber, int pageSize);

    Set<CategoryResponse> findAllCategoriesByVendorId(Long vendorId);

}
