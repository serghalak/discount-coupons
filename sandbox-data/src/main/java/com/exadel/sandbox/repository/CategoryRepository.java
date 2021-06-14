package com.exadel.sandbox.repository;

import com.exadel.sandbox.model.vendorinfo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    List<Category> findAll();


}
