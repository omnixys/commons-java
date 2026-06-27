package com.omnixys.commons.model;

import java.util.List;

public record ApiResponse<T>(
        boolean success,
        T data,
        String message,
        List<ValidationError> errors
) {
    public ApiResponse {
        if (errors == null) {
            errors = List.of();
        }
    }

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, data, null, List.of());
    }

    public static <T> ApiResponse<T> ok(T data, String message) {
        return new ApiResponse<>(true, data, message, List.of());
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, null, message, List.of());
    }

    public static <T> ApiResponse<T> error(String message, List<ValidationError> errors) {
        return new ApiResponse<>(false, null, message, errors);
    }
}
