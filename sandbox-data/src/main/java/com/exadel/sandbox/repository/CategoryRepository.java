package com.exadel.sandbox.repository;

import java.util.List;
import com.exadel.sandbox.model.vendorinfo.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    //List<Category> findAll();
    Category findByName(String categoryName);

    @Query(value = "SELECT * FROM category JOIN product ON category.id = product.category_id WHERE vendor_id = :id",
            nativeQuery = true)
    List<Category> findAllByVendorId(Long id);

}
