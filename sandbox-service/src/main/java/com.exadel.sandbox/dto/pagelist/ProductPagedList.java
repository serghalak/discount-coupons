package com.exadel.sandbox.dto.pagelist;

import com.exadel.sandbox.dto.response.product.ProductResponse;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class ProductPagedList extends PageImpl<ProductResponse> {

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ProductPagedList(@JsonProperty("content") List<ProductResponse> content,
                            @JsonProperty("number") int number,
                            @JsonProperty("size") int size,
                            @JsonProperty("totalElements") Long totalElements,
                            @JsonProperty("pageable") JsonNode pageable,
                            @JsonProperty("last") boolean last,
                            @JsonProperty("totalPages") int totalPages,
                            @JsonProperty("sort") JsonNode sort,
                            @JsonProperty("first") boolean first,
                            @JsonProperty("numberOfElements") int numberOfElements) {

        super(content, PageRequest.of(number, size), totalElements);
    }

    public ProductPagedList(List<ProductResponse> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public ProductPagedList(List<ProductResponse> content) {
        super(content);
    }

}