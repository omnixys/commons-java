package com.omnixys.commons.model;

import java.time.Instant;

public record TemporalRange(
        Instant start,
        Instant end
) {
    public TemporalRange {
        if (start == null) {
            throw new IllegalArgumentException("start must not be null");
        }
        if (end != null && end.isBefore(start)) {
            throw new IllegalArgumentException("end must not be before start");
        }
    }

    public boolean contains(Instant instant) {
        if (instant == null) return false;
        return !instant.isBefore(start) && (end == null || !instant.isAfter(end));
    }
}
