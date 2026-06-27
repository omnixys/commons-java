package com.omnixys.commons.model;

public record GeoLocation(
        Double latitude,
        Double longitude
) {
    public GeoLocation {
        if (latitude == null || latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("latitude must be between -90 and 90");
        }
        if (longitude == null || longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("longitude must be between -180 and 180");
        }
    }
}
