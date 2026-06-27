package com.omnixys.commons.error;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

public class BaseOmnixysException extends RuntimeException {

    @FunctionalInterface
    public interface ContextProvider {
        ExceptionContext resolve();
    }

    private static volatile ContextProvider contextProvider;

    public static void registerContextProvider(ContextProvider provider) {
        contextProvider = provider;
    }

    private final ErrorCode errorCode;
    private final String requestId;
    private final String correlationId;
    private final String traceId;
    private final String actorId;
    private final String tenantId;
    private final Map<String, Object> metadata;

    public BaseOmnixysException(ErrorCode errorCode, String message) {
        this(errorCode, message, null, null);
    }

    public BaseOmnixysException(ErrorCode errorCode, String message, Throwable cause) {
        this(errorCode, message, cause, null);
    }

    public BaseOmnixysException(ErrorCode errorCode, String message, ExceptionContext context) {
        this(errorCode, message, (Throwable) null, context);
    }

    public BaseOmnixysException(ErrorCode errorCode, String message, Throwable cause,
                                ExceptionContext context) {
        super(message, cause);
        this.errorCode = errorCode;
        ExceptionContext resolved = resolveContext(context);
        this.requestId = resolved != null ? resolved.requestId() : "unscoped";
        this.correlationId = resolved != null && resolved.correlationId() != null
                ? resolved.correlationId() : this.requestId;
        this.traceId = resolved != null ? resolved.traceId() : null;
        this.actorId = resolved != null ? resolved.actorId() : null;
        this.tenantId = resolved != null ? resolved.tenantId() : null;
        this.metadata = resolved != null && resolved.metadata() != null
                ? Collections.unmodifiableMap(Map.copyOf(resolved.metadata()))
                : Collections.emptyMap();
    }

    private static ExceptionContext resolveContext(ExceptionContext ctx) {
        if (ctx != null) return ctx;
        ContextProvider cp = contextProvider;
        return cp != null ? cp.resolve() : null;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public Optional<String> getTraceId() {
        return Optional.ofNullable(traceId);
    }

    public Optional<String> getActorId() {
        return Optional.ofNullable(actorId);
    }

    public Optional<String> getTenantId() {
        return Optional.ofNullable(tenantId);
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }
}
