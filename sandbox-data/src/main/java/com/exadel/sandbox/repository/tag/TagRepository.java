package com.exadel.sandbox.repository.tag;

import com.exadel.sandbox.model.vendorinfo.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long>, TagRepositoryCustom {
}