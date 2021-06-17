package com.exadel.sandbox.repository;

import com.exadel.sandbox.model.vendorinfo.Category;
import com.exadel.sandbox.model.vendorinfo.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "SELECT * FROM category JOIN product ON category.id = product.category_id WHERE vendor_id = :id",
            nativeQuery = true)
    List<Category> findAllByVendorId(Long id);


}
