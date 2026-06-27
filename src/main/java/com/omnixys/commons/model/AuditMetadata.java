package com.omnixys.commons.model;

import java.time.Instant;

public record AuditMetadata(
        Instant createdAt,
        Instant updatedAt,
        String createdBy,
        String updatedBy
) {
    public AuditMetadata {
        if (createdAt == null) {
            createdAt = Instant.now();
        }
    }
}
