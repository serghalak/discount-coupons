package com.exadel.sandbox.service;

import com.exadel.sandbox.dto.CategoryDto;
import com.exadel.sandbox.dto.pagelist.CategoryPagedList;
import org.springframework.data.domain.PageRequest;

public interface CategoryService {

    void deleteCategoryById(Long categoryId);

    CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto);

    CategoryPagedList listCategoriesByPartOfName(String categoryName, PageRequest pageRequest);

    CategoryDto findCategoryById(Long categoryId);

    CategoryPagedList listCategories(PageRequest pageRequest);

    CategoryDto saveCategory(CategoryDto categoryDto);

    boolean isCategoryNameExists(String categoryName);

}
