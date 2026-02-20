package com.aggregation.api.domain.valueobject;

public record CustomerId(String value) {
    public CustomerId {
        if (value == null  || value.isBlank()) {
            throw new IllegalArgumentException("Invalid customer id");
        }
    }
}
