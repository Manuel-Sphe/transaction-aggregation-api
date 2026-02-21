package com.aggregation.api.domain.valueobject;

public record TransactionSource(
        String source
) {
    public TransactionSource {
        if (source == null || source.isBlank()) {
            throw new IllegalArgumentException("Invalid transaction source");
        }
    }

}
