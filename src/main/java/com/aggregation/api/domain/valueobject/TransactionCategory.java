package com.aggregation.api.domain.valueobject;

public record TransactionCategory(String value) {

    public TransactionCategory {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Invalid transaction category");
        }
    }
}
