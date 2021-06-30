package com.exadel.sandbox.service;

import com.exadel.sandbox.dto.pagelist.PageList;
import com.exadel.sandbox.dto.response.tag.TagResponse;

import java.util.Set;

public interface TagService {

    PageList<TagResponse> findAll(Integer pageNumber, Integer pageSize);

}
