package com.aggregation.api.domain.valueobject;

public record TransactionCategory(String category) {

    public TransactionCategory {
        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException("Invalid transaction category");
        }
    }
}
