package com.omnixys.commons.error;

import java.util.Collections;
import java.util.Map;

public record DefaultExceptionContext(
        String requestId,
        String correlationId,
        String traceId,
        String actorId,
        String tenantId,
        Map<String, Object> metadata
) implements ExceptionContext {

    public DefaultExceptionContext {
        if (metadata == null) {
            metadata = Collections.emptyMap();
        }
    }

    public DefaultExceptionContext(String requestId) {
        this(requestId, requestId, null, null, null, Collections.emptyMap());
    }
}
