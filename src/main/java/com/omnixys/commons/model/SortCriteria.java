package com.omnixys.commons.model;

import com.omnixys.commons.enums.SortDirection;

public record SortCriteria(
        String field,
        SortDirection direction
) {
    public SortCriteria {
        if (field == null || field.isBlank()) {
            throw new IllegalArgumentException("field must not be blank");
        }
    }

    public static SortCriteria asc(String field) {
        return new SortCriteria(field, SortDirection.ASC);
    }

    public static SortCriteria desc(String field) {
        return new SortCriteria(field, SortDirection.DESC);
    }
}
