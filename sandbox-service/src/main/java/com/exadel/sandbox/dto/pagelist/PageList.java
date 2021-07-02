package com.exadel.sandbox.dto.pagelist;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PageList<E> {
    private List<E> elements;
    private long totalElements;
    private int totalPages;

    public <T> PageList(List<E> elements, Page<T> eventsPage) {
        this.elements = elements;
        this.totalElements = eventsPage.getTotalElements();
        this.totalPages = eventsPage.getTotalPages();
    }

}
