package com.exadel.sandbox.repository.category;

import com.exadel.sandbox.model.vendorinfo.Category;
import com.exadel.sandbox.repository.category.CategoryRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryRepositoryCustom {

    Category findByName(String categoryName);

    Set<Category> findAllByNameContainingIgnoreCaseOrderByNameAsc(String categoryName);

    Set<Category>findAllByOrderByNameAsc();

    List<Category>findDistinctByEventsVendorIdOrderByNameAsc(Long vendorId);
}