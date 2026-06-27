package com.omnixys.commons.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void shouldTruncate() {
        assertEquals("hel", StringUtils.truncate("hello", 3));
        assertNull(StringUtils.truncate(null, 3));
    }

    @Test
    void shouldMask() {
        assertEquals("***4567", StringUtils.mask("1234567", 4));
        assertEquals("12345", StringUtils.mask("12345", 5));
    }

    @Test
    void shouldSanitize() {
        assertEquals("hello world", StringUtils.sanitize("  hello   world  "));
        assertEquals("helloscript", StringUtils.sanitize("hello<script>"));
    }
}
