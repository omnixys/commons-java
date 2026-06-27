package com.omnixys.commons.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationUtilsTest {

    @Test
    void shouldValidateEmail() {
        assertTrue(ValidationUtils.isValidEmail("user@example.com"));
        assertFalse(ValidationUtils.isValidEmail("invalid"));
        assertFalse(ValidationUtils.isValidEmail(null));
    }

    @Test
    void shouldValidatePhone() {
        assertTrue(ValidationUtils.isValidPhone("+49123456789"));
        assertFalse(ValidationUtils.isValidPhone("abc"));
    }

    @Test
    void shouldValidateUrl() {
        assertTrue(ValidationUtils.isValidUrl("https://omnixys.com"));
        assertFalse(ValidationUtils.isValidUrl("not-a-url"));
    }

    @Test
    void shouldValidateUuid() {
        assertTrue(ValidationUtils.isValidUuid("550e8400-e29b-41d4-a716-446655440000"));
        assertFalse(ValidationUtils.isValidUuid("not-a-uuid"));
    }
}
