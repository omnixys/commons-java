package com.omnixys.commons.model;

import java.time.Instant;

public record TokenInfo(
        String token,
        Instant expiresAt,
        String tokenType
) {
    public TokenInfo {
        if (token == null || token.isBlank()) {
            throw new IllegalArgumentException("token must not be blank");
        }
    }
}
