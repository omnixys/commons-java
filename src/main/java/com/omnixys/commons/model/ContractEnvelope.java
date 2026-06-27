package com.omnixys.commons.model;

import java.time.Instant;

public record ContractEnvelope<T>(
        String schemaVersion,
        Instant occurredAt,
        ContractMetadata metadata,
        T payload
) {
    public ContractEnvelope {
        if (schemaVersion == null || schemaVersion.isBlank()) {
            throw new IllegalArgumentException("schemaVersion must not be blank");
        }
        if (metadata == null) {
            throw new IllegalArgumentException("metadata must not be null");
        }
        if (occurredAt == null) {
            occurredAt = Instant.now();
        }
    }
}
