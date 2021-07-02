package com.exadel.sandbox.repository.category;

import com.exadel.sandbox.model.vendorinfo.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryRepositoryCustom {

    Category findByName(String categoryName);

    Set<Category> findAllByNameContainingIgnoreCaseOrderByNameAsc(String categoryName);

    Set<Category> findAllByOrderByNameAsc();

    List<Category>findDistinctByEventsVendorIdOrderByNameAsc(Long vendorId);

    List<Category>findAllByVendorFilterIds(List<Long>ids);

    @Query("select distinct c.id from Category c " +
            "join c.tags tag " +
            "where tag.id in (?1)")
    Set<Long> findCategoryIdByTagsId(Set<Long> tagsId);
}