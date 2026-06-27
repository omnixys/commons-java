package com.omnixys.commons.model;

public record KeyValue<K, V>(
        K key,
        V value
) {}
