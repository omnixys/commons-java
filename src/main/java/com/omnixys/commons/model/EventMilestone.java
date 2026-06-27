package com.omnixys.commons.model;

import com.omnixys.commons.enums.EventMilestoneType;

import java.time.Instant;

public record EventMilestone(
        String milestoneId,
        EventMilestoneType type,
        String label,
        Instant occurredAt,
        String referenceId
) {
    public EventMilestone {
        if (milestoneId == null || milestoneId.isBlank()) {
            throw new IllegalArgumentException("milestoneId must not be blank");
        }
        if (type == null) {
            throw new IllegalArgumentException("type must not be null");
        }
        if (occurredAt == null) {
            occurredAt = Instant.now();
        }
    }
}
