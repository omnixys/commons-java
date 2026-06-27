package com.omnixys.commons.model;

public record TraceContext(
        String traceId,
        String spanId,
        String parentSpanId,
        String sampled
) {

    public boolean isValid() {
        return traceId != null && spanId != null;
    }
}
