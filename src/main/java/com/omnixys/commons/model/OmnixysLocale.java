package com.omnixys.commons.model;

public record OmnixysLocale(
        String language,
        String country
) {
    public OmnixysLocale {
        if (language == null || language.isBlank()) {
            throw new IllegalArgumentException("language must not be blank");
        }
    }

    public static OmnixysLocale GERMANY = new OmnixysLocale("de", "DE");
    public static OmnixysLocale US = new OmnixysLocale("en", "US");

    public static OmnixysLocale fromString(String tag) {
        if (tag == null || tag.isBlank()) {
            throw new IllegalArgumentException("tag must not be blank");
        }
        String[] parts = tag.split("-", 2);
        String lang = parts[0];
        String ctry = parts.length > 1 ? parts[1] : "";
        return new OmnixysLocale(lang, ctry);
    }

    @Override
    public String toString() {
        return country != null && !country.isBlank() ? language + "-" + country : language;
    }
}
