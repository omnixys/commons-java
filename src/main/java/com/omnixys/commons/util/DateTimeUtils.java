package com.omnixys.commons.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class DateTimeUtils {

    private static final DateTimeFormatter ISO_8601_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    private DateTimeUtils() {}

    public static String toIso8601(Instant instant) {
        if (instant == null) return null;
        return ISO_8601_FORMATTER.format(instant.atZone(ZoneOffset.UTC));
    }

    public static Instant parseIso8601(String value) {
        if (value == null || value.isBlank()) return null;
        try {
            return Instant.parse(value);
        } catch (DateTimeParseException e) {
            try {
                LocalDateTime ldt = LocalDateTime.parse(value, ISO_8601_FORMATTER);
                return ldt.toInstant(ZoneOffset.UTC);
            } catch (DateTimeParseException e2) {
                return null;
            }
        }
    }

    public static long toEpochMillis(Instant instant) {
        if (instant == null) return 0;
        return instant.toEpochMilli();
    }

    public static Instant fromEpochMillis(long epochMillis) {
        return Instant.ofEpochMilli(epochMillis);
    }

    public static boolean isValidIso8601(String value) {
        return parseIso8601(value) != null;
    }
}
