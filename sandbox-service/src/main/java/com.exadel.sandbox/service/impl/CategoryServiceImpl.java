package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.CategoryDto;
import com.exadel.sandbox.dto.pagelist.CategoryPagedList;
import com.exadel.sandbox.mappers.CategoryMapper;
import com.exadel.sandbox.model.vendorinfo.Category;
import com.exadel.sandbox.repository.CategoryRepository;
import com.exadel.sandbox.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    @Override
    public CategoryDto findCategoryById(Long categoryId) {

      log.debug(">>>>>>CategoryService find by Id: " + categoryId);

        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isPresent()){
            log.debug(">>>>>Category is found: " + categoryId);
            return categoryMapper.categoryToCategoryDto(category.get());
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found. UUID: " + categoryId);
        }

    }

    @Override
    public CategoryPagedList listCategories(PageRequest pageRequest) {

        CategoryPagedList categoryPagedList;
        Page<Category> categoryPage;
        categoryPage = categoryRepository.findAll(pageRequest);
        categoryPagedList=new CategoryPagedList(categoryPage
                .getContent()
                .stream()
                .map(categoryMapper::categoryToCategoryDto)
                .collect(Collectors.toList()),
                PageRequest
                    .of(categoryPage.getPageable().getPageNumber(), categoryPage.getPageable().getPageSize()),
                categoryPage.getTotalElements()  );

        return categoryPagedList;
    }

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {

        Category category = categoryMapper.categoryDtoToCategory(categoryDto);

        Category savedCategory = categoryRepository.save(category);
        if(savedCategory==null)
            return null;

        return categoryMapper.categoryToCategoryDto(savedCategory);
    }
}
