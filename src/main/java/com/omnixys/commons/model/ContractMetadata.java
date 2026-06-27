package com.omnixys.commons.model;

public record ContractMetadata(
        String requestId,
        String correlationId,
        String traceId,
        String actorId,
        String tenantId
) {}
