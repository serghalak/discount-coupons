package com.exadel.sandbox.repository.tag;

import com.exadel.sandbox.model.notification.SubscriptionResult;
import com.exadel.sandbox.model.vendorinfo.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface TagRepository extends JpaRepository<Tag, Long>, TagRepositoryCustom {

    @Query("select distinct tag from Tag tag " +
            "where (tag.id in (?1))")
    Set<Tag> getTagsById(Set<Long> tagsId);

    Set<Tag> findTagsByCategoryId(Long categoryId);

    @Query("select distinct new com.exadel.sandbox.model.notification.SubscriptionResult(t.id, t.name, false) " +
            "from Tag t  order by t.name")
    Set<SubscriptionResult> findAllTagAsSubscriptionResult();

}