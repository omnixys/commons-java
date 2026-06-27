package com.omnixys.commons.error.handler;

import com.omnixys.commons.error.BaseOmnixysException;
import com.omnixys.commons.error.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseOmnixysException.class)
    public ResponseEntity<Map<String, Object>> handleBaseOmnixysException(BaseOmnixysException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", Instant.now().toString());
        body.put("status", resolveHttpStatus(ex.getErrorCode()).value());
        body.put("error", ex.getErrorCode().name());
        body.put("message", ex.getMessage());
        body.put("requestId", ex.getRequestId());
        body.put("correlationId", ex.getCorrelationId());
        ex.getTraceId().ifPresent(t -> body.put("traceId", t));
        ex.getActorId().ifPresent(a -> body.put("actorId", a));
        ex.getTenantId().ifPresent(t -> body.put("tenantId", t));

        Map<String, Object> metadata = ex.getMetadata();
        if (metadata != null && !metadata.isEmpty()) {
            body.put("metadata", metadata);
        }

        return new ResponseEntity<>(body, resolveHttpStatus(ex.getErrorCode()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {
        List<Map<String, String>> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> {
                    Map<String, String> m = new LinkedHashMap<>();
                    m.put("field", fe.getField());
                    m.put("message", fe.getDefaultMessage());
                    return m;
                })
                .collect(Collectors.toList());
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", Instant.now().toString());
        body.put("status", 400);
        body.put("error", ErrorCode.VALIDATION_ERROR.name());
        body.put("message", "Validation failed");
        body.put("errors", errors);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", Instant.now().toString());
        body.put("status", 500);
        body.put("error", ErrorCode.INTERNAL_SERVER_ERROR.name());
        body.put("message", ex.getMessage() != null ? ex.getMessage() : "An unexpected error occurred");
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @SuppressWarnings("checkstyle:MethodLength")
    private HttpStatus resolveHttpStatus(ErrorCode errorCode) {
        return switch (errorCode) {
            // ── Authentication (401) ──
            case UNAUTHENTICATED,
                 INVALID_CREDENTIALS, ACCOUNT_DISABLED, EMAIL_NOT_VERIFIED,
                 REFRESH_TOKEN_EXPIRED, DEVICE_NOT_TRUSTED, SESSION_EXPIRED,
                 TOKEN_REVOKED, STEP_UP_REQUIRED, ACCESS_BLOCKED,
                 AUTHENTICATION_INPUT_INVALID, AUTHENTICATION_STATE_INVALID,
                 GUEST_SIGNUP_FAILED,
                 TICKET_TOKEN_INVALID, TICKET_VERIFICATION_TOKEN_INVALID,
                 EVENT_TOKEN_INVALID, SEAT_VERIFICATION_TOKEN_INVALID,
                 USER_STATE_INVALID ->
                    HttpStatus.UNAUTHORIZED;

            // ── Authorization (403) ──
            case UNAUTHORIZED, FORBIDDEN, ACCESS_DENIED,
                 UNAUTHORIZED_TENANT,
                 EVENT_ACCESS_DENIED, SEAT_ACCESS_DENIED, TICKET_ACCESS_DENIED,
                 INVITATION_ACCESS_DENIED, CHAT_ACCESS_DENIED ->
                    HttpStatus.FORBIDDEN;

            // ── Validation / client errors (400) ──
            case VALIDATION_ERROR,
                 EVENT_INVALID_INPUT, SEAT_EVENT_MISMATCH,
                 INVITATION_INVALID_INPUT, INVITATION_PREVIEW_FAILED,
                 INVITATION_UPLOAD_INVALID,
                 MISSING_GUEST_NAME, MISSING_PENDING_CONTACT,
                 MISSING_RSVP_CONTACT_DETAILS, MISSING_CONTACT_METHOD,
                 TICKET_DEVICE_KEY_INVALID, TICKET_NONCE_UNINITIALIZED,
                 TICKET_ALREADY_EXISTS, TICKET_DEVICE_ALREADY_BOUND,
                 NOTIFICATION_INPUT_INVALID, NOTIFICATION_STATE_INVALID,
                 TEMPLATE_STATE_INVALID,
                 CHAT_STATE_INVALID, CHAT_ASSIGNMENT_CONFLICT,
                 MESSAGE_INPUT_INVALID,
                 RSVP_NOT_SUBMITTED, RSVP_NOT_ACCEPTED ->
                    HttpStatus.BAD_REQUEST;

            // ── Not Found (404) ──
            case USER_NOT_FOUND, EVENT_NOT_FOUND, SEAT_NOT_FOUND,
                 INVITATION_NOT_FOUND, TICKET_NOT_FOUND,
                 NOTIFICATION_NOT_FOUND,
                 EVENT_TIMELINE_NOT_FOUND, EVENT_MEDIA_NOT_FOUND,
                 EVENT_MEDIA_VARIANT_NOT_FOUND, EVENT_MEMBER_NOT_FOUND,
                 SECTION_NOT_FOUND, TABLE_NOT_FOUND, LAYOUT_VERSION_NOT_FOUND,
                 SEAT_ASSIGNMENT_NOT_FOUND,
                 TEMPLATE_NOT_FOUND, CHAT_NOT_FOUND ->
                    HttpStatus.NOT_FOUND;

            // ── Conflict (409) ──
            case USER_ALREADY_EXISTS, USERNAME_ALREADY_EXISTS,
                 USER_EMAIL_ALREADY_EXISTS,
                 EVENT_ALREADY_EXISTS, EVENT_CLOSED,
                 INVITATION_ALREADY_EXISTS, INVITATION_ALREADY_APPROVED,
                 INVITATION_ALREADY_REJECTED, INVITATION_LIMIT_REACHED,
                 RSVP_ALREADY_ACCEPTED,
                 TICKET_ALREADY_SCANNED, TICKET_ALREADY_REDEEMED,
                 TICKET_REVOKED,
                 SEAT_ALREADY_RESERVED, SEAT_OCCUPIED, SEAT_CAPACITY_EXCEEDED,
                 SEAT_UNAVAILABLE, SEAT_ALLOCATION_EXCEEDED,
                 SECTION_CONFLICT, TABLE_CONFLICT,
                 TEMPLATE_ALREADY_EXISTS ->
                    HttpStatus.CONFLICT;

            // ── Too Many Requests (429) ──
            case RATE_LIMIT_EXCEEDED -> HttpStatus.TOO_MANY_REQUESTS;

            // ── Internal Server Error (500) ──
            case INTERNAL_SERVER_ERROR,
                 STORAGE_ERROR, CACHE_PARSE_ERROR, CACHE_VALIDATION_ERROR ->
                    HttpStatus.INTERNAL_SERVER_ERROR;

            // ── Service Unavailable (503) ──
            case SERVICE_UNAVAILABLE, KAFKA_UNAVAILABLE, CACHE_UNAVAILABLE,
                 MINIO_UNAVAILABLE, NETWORK_ERROR,
                 GEOCODING_UNAVAILABLE, IDENTITY_PROVIDER_UNAVAILABLE,
                 NOTIFICATION_CHANNEL_UNAVAILABLE, NOTIFICATION_DELIVERY_FAILED ->
                    HttpStatus.SERVICE_UNAVAILABLE;

            // ── Catch-all: treat unknown client errors as 400 ──
            default -> HttpStatus.BAD_REQUEST;
        };
    }
}
