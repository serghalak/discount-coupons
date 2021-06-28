package com.exadel.sandbox.repository.category.impl;

import com.exadel.sandbox.model.vendorinfo.Category;
import com.exadel.sandbox.repository.category.CategoryRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryCustomImpl implements CategoryRepositoryCustom {

    private final EntityManager entityManager;

    @Override
    public List<Category> findAllByVendorId(Long vendorId) {
        return entityManager.createNativeQuery(
                "SELECT DISTINCT category.*\n" +
                        "FROM category\n" +
                        "         JOIN event ON category.id = event.category_id\n" +
                        "WHERE vendor_id = ?\n" +
                        "ORDER BY name",
                Category.class)
                .setParameter(1, vendorId)
                .getResultList();
    }
}
