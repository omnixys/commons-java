package com.omnixys.commons.model;

import com.omnixys.commons.enums.PhoneNumberType;

public record PhoneNumber(
        PhoneNumberType type,
        String countryCode,
        String number,
        String label,
        boolean isPrimary
) {
    public PhoneNumber {
        if (countryCode == null || countryCode.isBlank()) {
            throw new IllegalArgumentException("countryCode must not be blank");
        }
        if (number == null || number.isBlank()) {
            throw new IllegalArgumentException("number must not be blank");
        }
    }
}
