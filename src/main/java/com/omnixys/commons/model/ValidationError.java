package com.omnixys.commons.model;

public record ValidationError(
        String field,
        String message,
        String code
) {
    public ValidationError {
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("message must not be blank");
        }
    }
}
