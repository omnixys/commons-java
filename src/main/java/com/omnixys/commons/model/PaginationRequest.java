package com.omnixys.commons.model;

import java.util.List;

public record PaginationRequest(
        int page,
        int size,
        List<SortCriteria> sort
) {
    public PaginationRequest {
        if (page < 0) {
            throw new IllegalArgumentException("page must not be negative");
        }
        if (size < 1) {
            throw new IllegalArgumentException("size must be at least 1");
        }
        if (sort == null) {
            sort = List.of();
        }
    }

    public PaginationRequest(int page, int size) {
        this(page, size, List.of());
    }
}
