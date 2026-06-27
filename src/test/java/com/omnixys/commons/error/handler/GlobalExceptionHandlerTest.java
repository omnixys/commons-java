package com.omnixys.commons.error.handler;

import com.omnixys.commons.error.BaseOmnixysException;
import com.omnixys.commons.error.ErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
    }

    @Test
    void shouldMapAuthErrorsTo401() {
        assertStatus(ErrorCode.UNAUTHENTICATED, 401);
        assertStatus(ErrorCode.INVALID_CREDENTIALS, 401);
        assertStatus(ErrorCode.SESSION_EXPIRED, 401);
        assertStatus(ErrorCode.TOKEN_REVOKED, 401);
    }

    @Test
    void shouldMapAuthorizationErrorsTo403() {
        assertStatus(ErrorCode.UNAUTHORIZED, 403);
        assertStatus(ErrorCode.FORBIDDEN, 403);
        assertStatus(ErrorCode.ACCESS_DENIED, 403);
        assertStatus(ErrorCode.EVENT_ACCESS_DENIED, 403);
    }

    @Test
    void shouldMapValidationErrorsTo400() {
        assertStatus(ErrorCode.VALIDATION_ERROR, 400);
        assertStatus(ErrorCode.EVENT_INVALID_INPUT, 400);
        assertStatus(ErrorCode.MISSING_GUEST_NAME, 400);
        assertStatus(ErrorCode.TICKET_DEVICE_KEY_INVALID, 400);
    }

    @Test
    void shouldMapNotFoundErrorsTo404() {
        assertStatus(ErrorCode.USER_NOT_FOUND, 404);
        assertStatus(ErrorCode.EVENT_NOT_FOUND, 404);
        assertStatus(ErrorCode.SEAT_NOT_FOUND, 404);
        assertStatus(ErrorCode.TEMPLATE_NOT_FOUND, 404);
    }

    @Test
    void shouldMapConflictErrorsTo409() {
        assertStatus(ErrorCode.USER_ALREADY_EXISTS, 409);
        assertStatus(ErrorCode.EVENT_ALREADY_EXISTS, 409);
        assertStatus(ErrorCode.SEAT_ALREADY_RESERVED, 409);
        assertStatus(ErrorCode.TICKET_ALREADY_SCANNED, 409);
        assertStatus(ErrorCode.SEAT_ALLOCATION_EXCEEDED, 409);
    }

    @Test
    void shouldMapRateLimitTo429() {
        assertStatus(ErrorCode.RATE_LIMIT_EXCEEDED, 429);
    }

    @Test
    void shouldMapServiceUnavailableTo503() {
        assertStatus(ErrorCode.SERVICE_UNAVAILABLE, 503);
        assertStatus(ErrorCode.KAFKA_UNAVAILABLE, 503);
        assertStatus(ErrorCode.CACHE_UNAVAILABLE, 503);
        assertStatus(ErrorCode.MINIO_UNAVAILABLE, 503);
        assertStatus(ErrorCode.NOTIFICATION_DELIVERY_FAILED, 503);
    }

    @Test
    void shouldMapInternalServerErrorTo500() {
        assertStatus(ErrorCode.INTERNAL_SERVER_ERROR, 500);
        assertStatus(ErrorCode.STORAGE_ERROR, 500);
        assertStatus(ErrorCode.CACHE_PARSE_ERROR, 500);
        assertStatus(ErrorCode.CACHE_VALIDATION_ERROR, 500);
    }

    @Test
    void shouldIncludeRequestMetadataInResponse() {
        var ex = new BaseOmnixysException(ErrorCode.USER_NOT_FOUND, "User missing");
        ResponseEntity<Map<String, Object>> response = handler.handleBaseOmnixysException(ex);

        assertEquals(404, response.getStatusCode().value());
        Map<String, Object> body = response.getBody();
        assertNotNull(body);
        assertEquals("USER_NOT_FOUND", body.get("error"));
        assertEquals("User missing", body.get("message"));
        assertEquals("unscoped", body.get("requestId"));
        assertNotNull(body.get("timestamp"));
    }

    @Test
    void shouldHandleGenericException() {
        ResponseEntity<Map<String, Object>> response = handler.handleGenericException(
                new RuntimeException("oops"));

        assertEquals(500, response.getStatusCode().value());
        Map<String, Object> body = response.getBody();
        assertNotNull(body);
        assertEquals("INTERNAL_SERVER_ERROR", body.get("error"));
        assertEquals("oops", body.get("message"));
    }

    private void assertStatus(ErrorCode errorCode, int expectedStatus) {
        var ex = new BaseOmnixysException(errorCode, "test");
        ResponseEntity<Map<String, Object>> response = handler.handleBaseOmnixysException(ex);
        assertEquals(expectedStatus, response.getStatusCode().value(),
                "Expected " + expectedStatus + " for " + errorCode);
    }
}
