package com.omnixys.commons.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaginationResponseTest {

    @Test
    void shouldCalculateTotalPages() {
        var resp = new PaginationResponse<>(List.of("a", "b"), 10, 0, 2);
        assertEquals(5, resp.totalPages());
    }

    @Test
    void shouldDetectHasNext() {
        var resp = new PaginationResponse<>(List.of("a", "b"), 10, 0, 2);
        assertTrue(resp.hasNext());
        assertFalse(resp.hasPrevious());
    }

    @Test
    void shouldDetectLastPage() {
        var resp = new PaginationResponse<>(List.of("a", "b"), 10, 4, 2);
        assertFalse(resp.hasNext());
        assertTrue(resp.hasPrevious());
    }
}
