package com.omnixys.commons.model;

import com.omnixys.commons.enums.EventMediaType;

public record EventMediaUpload(
        String mediaId,
        String key,
        String filename,
        String mimetype,
        Long size,
        EventMediaType type
) {
    public EventMediaUpload {
        if (mediaId == null || mediaId.isBlank()) {
            throw new IllegalArgumentException("mediaId must not be blank");
        }
        if (type == null) {
            throw new IllegalArgumentException("type must not be null");
        }
    }
}
