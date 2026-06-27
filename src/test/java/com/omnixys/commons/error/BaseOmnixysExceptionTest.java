package com.omnixys.commons.error;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BaseOmnixysExceptionTest {

    @Test
    void shouldCreateWithErrorCodeAndMessage() {
        var ex = new BaseOmnixysException(ErrorCode.USER_NOT_FOUND, "User not found");
        assertEquals(ErrorCode.USER_NOT_FOUND, ex.getErrorCode());
        assertEquals("User not found", ex.getMessage());
        assertEquals("unscoped", ex.getRequestId());
        assertEquals("unscoped", ex.getCorrelationId());
        assertTrue(ex.getTraceId().isEmpty());
        assertTrue(ex.getActorId().isEmpty());
        assertTrue(ex.getTenantId().isEmpty());
        assertTrue(ex.getMetadata().isEmpty());
    }

    @Test
    void shouldCreateWithErrorCodeMessageAndCause() {
        var cause = new RuntimeException("DB error");
        var ex = new BaseOmnixysException(ErrorCode.INTERNAL_SERVER_ERROR, "Server error", cause);
        assertEquals(ErrorCode.INTERNAL_SERVER_ERROR, ex.getErrorCode());
        assertEquals("Server error", ex.getMessage());
        assertSame(cause, ex.getCause());
    }

    @Test
    void shouldCreateWithContext() {
        var ex = new BaseOmnixysException(ErrorCode.FORBIDDEN, "Access denied",
                new ExceptionContext() {
                    public String requestId() { return "req-123"; }
                    public String correlationId() { return "corr-456"; }
                    public String traceId() { return "trace-789"; }
                    public String actorId() { return "user-1"; }
                    public String tenantId() { return "tenant-a"; }
                    public Map<String, Object> metadata() { return Map.of("reason", "no permission"); }
                });
        assertEquals("req-123", ex.getRequestId());
        assertEquals("corr-456", ex.getCorrelationId());
        assertTrue(ex.getTraceId().isPresent());
        assertEquals("trace-789", ex.getTraceId().get());
        assertTrue(ex.getActorId().isPresent());
        assertEquals("user-1", ex.getActorId().get());
        assertTrue(ex.getTenantId().isPresent());
        assertEquals("tenant-a", ex.getTenantId().get());
        assertEquals("no permission", ex.getMetadata().get("reason"));
    }

    @Test
    void shouldDefaultCorrelationIdToRequestId() {
        var ex = new BaseOmnixysException(ErrorCode.VALIDATION_ERROR, "Invalid input",
                new ExceptionContext() {
                    public String requestId() { return "req-123"; }
                    public String correlationId() { return null; }
                    public String traceId() { return null; }
                    public String actorId() { return null; }
                    public String tenantId() { return null; }
                    public Map<String, Object> metadata() { return Map.of(); }
                });
        assertEquals("req-123", ex.getRequestId());
        assertEquals("req-123", ex.getCorrelationId());
    }

    @Test
    void shouldReturnImmutableMetadata() {
        var mutable = new java.util.HashMap<String, Object>();
        mutable.put("key", "value");
        var ex = new BaseOmnixysException(ErrorCode.SERVICE_UNAVAILABLE, "Down",
                new ExceptionContext() {
                    public String requestId() { return "r1"; }
                    public String correlationId() { return "c1"; }
                    public String traceId() { return null; }
                    public String actorId() { return null; }
                    public String tenantId() { return null; }
                    public Map<String, Object> metadata() { return mutable; }
                });
        assertEquals("value", ex.getMetadata().get("key"));
        assertThrows(UnsupportedOperationException.class, () -> ex.getMetadata().put("new", "val"));
    }

    @Test
    void shouldCreateSubclassException() {
        var ex = new UserNotFoundException("User not found");
        assertEquals(ErrorCode.USER_NOT_FOUND, ex.getErrorCode());
        assertEquals("User not found", ex.getMessage());
        assertEquals("unscoped", ex.getRequestId());
    }

    @Test
    void shouldCreateSubclassExceptionWithCause() {
        var cause = new RuntimeException("cause");
        var ex = new UserNotFoundException("User not found", cause);
        assertEquals(ErrorCode.USER_NOT_FOUND, ex.getErrorCode());
        assertSame(cause, ex.getCause());
    }

    @Test
    void shouldFallbackToContextProviderWhenContextIsNull() {
        try {
            BaseOmnixysException.registerContextProvider(() -> new ExceptionContext() {
                public String requestId() { return "fallback-req"; }
                public String correlationId() { return "fallback-corr"; }
                public String traceId() { return "fallback-trace"; }
                public String actorId() { return "fallback-actor"; }
                public String tenantId() { return "fallback-tenant"; }
                public Map<String, Object> metadata() { return Map.of("from", "provider"); }
            });

            var ex = new BaseOmnixysException(ErrorCode.USER_NOT_FOUND, "test");
            assertEquals("fallback-req", ex.getRequestId());
            assertEquals("fallback-corr", ex.getCorrelationId());
            assertTrue(ex.getTraceId().isPresent());
            assertEquals("fallback-trace", ex.getTraceId().get());
            assertTrue(ex.getActorId().isPresent());
            assertEquals("fallback-actor", ex.getActorId().get());
            assertTrue(ex.getTenantId().isPresent());
            assertEquals("fallback-tenant", ex.getTenantId().get());
            assertEquals("provider", ex.getMetadata().get("from"));
        } finally {
            BaseOmnixysException.registerContextProvider(null);
        }
    }

    @Test
    void shouldNotUseContextProviderWhenContextIsExplicitlyProvided() {
        try {
            BaseOmnixysException.registerContextProvider(() -> {
                throw new AssertionError("provider should not be called");
            });

            var ex = new BaseOmnixysException(ErrorCode.FORBIDDEN, "msg",
                    new ExceptionContext() {
                        public String requestId() { return "explicit-req"; }
                        public String correlationId() { return "explicit-corr"; }
                        public String traceId() { return null; }
                        public String actorId() { return null; }
                        public String tenantId() { return null; }
                        public Map<String, Object> metadata() { return Map.of(); }
                    });
            assertEquals("explicit-req", ex.getRequestId());
            assertEquals("explicit-corr", ex.getCorrelationId());
        } finally {
            BaseOmnixysException.registerContextProvider(null);
        }
    }

    @Test
    void shouldDefaultToUnscopedWhenProviderReturnsNull() {
        try {
            BaseOmnixysException.registerContextProvider(() -> null);

            var ex = new BaseOmnixysException(ErrorCode.VALIDATION_ERROR, "test");
            assertEquals("unscoped", ex.getRequestId());
            assertEquals("unscoped", ex.getCorrelationId());
        } finally {
            BaseOmnixysException.registerContextProvider(null);
        }
    }

    @Test
    void shouldDefaultToUnscopedWhenProviderPartiallyReturnsNulls() {
        try {
            BaseOmnixysException.registerContextProvider(() -> new ExceptionContext() {
                public String requestId() { return "req-only"; }
                public String correlationId() { return null; }
                public String traceId() { return null; }
                public String actorId() { return null; }
                public String tenantId() { return null; }
                public Map<String, Object> metadata() { return null; }
            });

            var ex = new BaseOmnixysException(ErrorCode.VALIDATION_ERROR, "test");
            assertEquals("req-only", ex.getRequestId());
            assertEquals("req-only", ex.getCorrelationId());
            assertTrue(ex.getTraceId().isEmpty());
            assertTrue(ex.getActorId().isEmpty());
            assertTrue(ex.getTenantId().isEmpty());
            assertTrue(ex.getMetadata().isEmpty());
        } finally {
            BaseOmnixysException.registerContextProvider(null);
        }
    }
}
