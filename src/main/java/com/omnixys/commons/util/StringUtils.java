package com.omnixys.commons.util;

public final class StringUtils {

    private StringUtils() {}

    public static String truncate(String value, int maxLength) {
        if (value == null) return null;
        if (value.length() <= maxLength) return value;
        return value.substring(0, maxLength);
    }

    public static String mask(String value, int visibleChars) {
        if (value == null) return null;
        if (value.length() <= visibleChars) return value;
        int maskLen = value.length() - visibleChars;
        return "*".repeat(maskLen) + value.substring(maskLen);
    }

    public static String maskEmail(String email) {
        if (email == null) return null;
        int atIndex = email.indexOf('@');
        if (atIndex <= 1) return email;
        return email.charAt(0) + "***" + email.substring(atIndex);
    }

    public static String sanitize(String value) {
        if (value == null) return null;
        return value.strip()
                .replaceAll("\\s+", " ")
                .replaceAll("[<>\"'&{}]", "");
    }

    public static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    public static boolean isNotBlank(String value) {
        return !isBlank(value);
    }
}
