package com.exadel.sandbox.repository.tag;

import com.exadel.sandbox.model.vendorinfo.Tag;

import java.util.List;

public interface TagRepositoryCustom {

    List<Tag>findAllByCategoryFilter(List<Long> ids);
}
