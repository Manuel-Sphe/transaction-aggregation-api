package com.aggregation.api.domain.valueobject;

public record Merchant(String name) {

    public Merchant {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Merchant name required");
        }
    }
}
