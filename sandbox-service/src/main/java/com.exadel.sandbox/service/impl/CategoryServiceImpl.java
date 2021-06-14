package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.CategoryDto;
import com.exadel.sandbox.mappers.CategoryMapper;
import com.exadel.sandbox.model.vendorinfo.Category;
import com.exadel.sandbox.repository.CategoryRepository;
import com.exadel.sandbox.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> listCategories() {

        return categoryRepository.findAll();
    }

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {

        Category category = categoryMapper.categoryDtoToCategory(categoryDto);
        Category savedCategory = categoryRepository.save(category);

        return categoryMapper.categoryToCategoryDto(savedCategory);
    }
}
