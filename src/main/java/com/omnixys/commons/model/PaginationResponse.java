package com.omnixys.commons.model;

import java.util.List;

public record PaginationResponse<T>(
        List<T> items,
        long total,
        int page,
        int size
) {
    public PaginationResponse {
        if (items == null) {
            items = List.of();
        }
    }

    public int totalPages() {
        return size > 0 ? (int) Math.ceil((double) total / size) : 0;
    }

    public boolean hasNext() {
        return page < totalPages() - 1;
    }

    public boolean hasPrevious() {
        return page > 0;
    }
}
