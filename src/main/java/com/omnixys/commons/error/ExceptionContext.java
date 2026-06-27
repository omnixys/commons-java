package com.omnixys.commons.error;

import java.util.Map;

public interface ExceptionContext {

    String requestId();

    String correlationId();

    String traceId();

    String actorId();

    String tenantId();

    Map<String, Object> metadata();
}
