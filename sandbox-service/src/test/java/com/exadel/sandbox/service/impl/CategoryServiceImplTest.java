package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.response.category.CategoryResponse;
import com.exadel.sandbox.mappers.category.CategoryMapper;
import com.exadel.sandbox.mappers.category.CategoryShortMapper;
import com.exadel.sandbox.model.vendorinfo.Category;
import com.exadel.sandbox.model.vendorinfo.Event;
import com.exadel.sandbox.repository.category.CategoryRepository;
import com.exadel.sandbox.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 10;
    private static final String DEFAULT_FIELD_SORT = "name";

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private CategoryMapper categoryMapper;
    @Mock
    private CategoryShortMapper categoryShortMapper;

    private Category category;

    @BeforeEach
    void setUp() {
        category=new Category();
        category.setId(1L);
        category.setName("some category");
        category.setDescription("some description");
    }

    @Test
    void deleteExistsCategoryByIdAndEmptyEvent() {

        when(categoryRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(category));

        assertDoesNotThrow(
                () -> {
                    categoryService.deleteCategoryById(1L);
                });
    }

    @Test
    void deleteNoExistsCategoryById() {

        when(categoryRepository.findById(2L)).thenReturn(java.util.Optional.ofNullable(null));

        RuntimeException exception = assertThrows(ResponseStatusException.class,
                () -> {
                    categoryService.deleteCategoryById(2L);
                });
    }

    @Test
    void deleteExistsCategoryByIdAndExistsEvent() {

        when(categoryRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(category));

        Event event = new Event();
        category.setEvents(new HashSet<Event>(Arrays.asList(event)));

        RuntimeException exception = assertThrows(ResponseStatusException.class,
                () -> {
                    categoryService.deleteCategoryById(1L);
                });
    }

    @Test
    void updateCategory() {
    }

    @Test
    void listCategoriesByPartOfName() {
    }

    @Test
    void findCategoryById() {

        when(categoryRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(category));

        assertDoesNotThrow(
                () -> {
                    categoryService.findCategoryById(1L);
                });
    }

    @Test
    void findCategoryByIdCheckReturnType() {

        when(categoryRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(category));

        System.out.println(categoryService.
                findCategoryById(1L));
        assertEquals(CategoryResponse.class,
                categoryService.findCategoryById(1L).getClass());

    }

    @Test
    void listCategories() {
    }

    @Test
    void saveCategory() {
    }

    @Test
    void isCategoryNameExists() {
    }

    @Test
    void findAllCategoriesByVendorId() {
    }

    @Test
    void findAllByVendorId() {
    }

    @Test
    void findAllCategoryByLocationFilter() {
    }

    @Test
    void findAllCategoryByVendorFilter() {
    }

    @Test
    void findAllCategoryFilter() {
    }
}