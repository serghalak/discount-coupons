package com.exadel.sandbox.service;

import com.exadel.sandbox.dto.pagelist.CategoryPagedList;
import com.exadel.sandbox.dto.request.category.CategoryRequest;
import com.exadel.sandbox.dto.response.category.CategoryResponse;
import org.springframework.data.domain.PageRequest;

public interface CategoryService {

    void deleteCategoryById(Long categoryId);

    CategoryResponse updateCategory(Long categoryId, CategoryRequest categoryRequest);

    CategoryPagedList listCategoriesByPartOfName(String categoryName, PageRequest pageRequest);

    CategoryResponse findCategoryById(Long categoryId);

    //CategoryPagedList listCategories(PageRequest pageRequest);

    CategoryResponse saveCategory(CategoryRequest categoryRequest);

    boolean isCategoryNameExists(String categoryName);

    CategoryPagedList listCategories(int pageNumber, int pageSize);

}
