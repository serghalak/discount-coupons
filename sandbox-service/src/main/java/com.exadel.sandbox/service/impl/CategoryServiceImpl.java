package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.CategoryDto;
import com.exadel.sandbox.dto.pagelist.CategoryPagedList;
import com.exadel.sandbox.mappers.CategoryMapper;
import com.exadel.sandbox.model.vendorinfo.Category;
import com.exadel.sandbox.model.vendorinfo.Product;
import com.exadel.sandbox.repository.CategoryRepository;
import com.exadel.sandbox.service.CategoryService;
import com.exadel.sandbox.service.ProductService;
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
    private ProductService productService;


    @Override
    public void deleteCategoryById(Long categoryId) {

        log.debug(">>>>>>>>delete category id " + categoryId);

        if(productService.isCategoryIdUses(categoryId)){
            categoryRepository.deleteById(categoryId);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_MODIFIED
                    , "You cannot delete category. Category is uses");
        }

    }

    @Override
    public CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto) {

        log.debug(">>>>>update category with id: " + categoryId);

        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);


        categoryOptional.ifPresentOrElse(category -> {
            category.setName(categoryDto.getName());
            category.setDescription(categoryDto.getDescription());
            categoryRepository.save(category);
        }, () -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found. CategoryId: " + categoryId);
        });

        Optional<Category> updateCategory=categoryRepository.findById(categoryId);
        if(updateCategory.isPresent()){
            return categoryMapper.categoryToCategoryDto(updateCategory.get());
        }
        return null;
    }

    @Override
    public CategoryPagedList listCategoriesByPartOfName(String categoryName, PageRequest pageRequest) {

        log.debug(">>>>>>>>>>>>>ListCategoryByPartOfName ...." + categoryName);

        CategoryPagedList categoryPagedList;
        Page<Category> categoryPage;
        categoryPage = categoryRepository.findAll(pageRequest);
        categoryPagedList=new CategoryPagedList(categoryPage
                .getContent()
                .stream()
                .filter(category ->  category.getName().matches("(.*)" + categoryName + "(.*)"))//add search use only part of category name
                .map(categoryMapper::categoryToCategoryDto)
                .collect(Collectors.toList()),
                PageRequest
                        .of(categoryPage.getPageable().getPageNumber(), categoryPage.getPageable().getPageSize()),
                categoryPage.getTotalElements()  );

        return categoryPagedList;
    }

    @Override
    public CategoryDto findCategoryById(Long categoryId) {

      log.debug(">>>>>>CategoryService find by Id: " + categoryId);

        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isPresent()){
            log.debug(">>>>>Category is found: " + categoryId);
            return categoryMapper.categoryToCategoryDto(category.get());
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found Category" );
        }

    }

    @Override
    public CategoryPagedList listCategories(PageRequest pageRequest) {

        log.debug(">>>>>>>>>>>>>ListCategory ....");

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

    @Override
    public boolean isCategoryNameExists(String categoryName) {
        Category categoryByName = categoryRepository.findByName(categoryName);
        if(categoryByName==null){
            return false;
        }
        return true;
    }
}
