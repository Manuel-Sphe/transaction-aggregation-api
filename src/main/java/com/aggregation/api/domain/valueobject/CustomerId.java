package com.aggregation.api.domain.valueobject;

public record CustomerId(String customerId) {
    public CustomerId {
        if (customerId == null  || customerId.isBlank()) {
            throw new IllegalArgumentException("Invalid customer id");
        }
    }
}
