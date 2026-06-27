package com.omnixys.commons.model;

import com.omnixys.commons.enums.LogLevel;

import java.time.Instant;
import java.util.Map;

public record LogEntry(
        LogLevel level,
        String message,
        String service,
        Instant timestamp,
        Map<String, Object> metadata,
        TraceContext traceContext,
        String operation
) {
    public LogEntry {
        if (level == null) {
            throw new IllegalArgumentException("level must not be null");
        }
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("message must not be blank");
        }
        if (timestamp == null) {
            timestamp = Instant.now();
        }
        if (metadata == null) {
            metadata = Map.of();
        }
    }
}
