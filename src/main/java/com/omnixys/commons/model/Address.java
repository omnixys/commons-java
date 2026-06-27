package com.omnixys.commons.model;

public record Address(
        String street,
        String houseNumber,
        String postalCode,
        String city,
        String state,
        String country,
        String additionalInfo
) {
    public Address {
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("city must not be blank");
        }
        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("country must not be blank");
        }
    }
}
