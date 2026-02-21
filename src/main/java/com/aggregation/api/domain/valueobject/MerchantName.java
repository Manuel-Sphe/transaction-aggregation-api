package com.aggregation.api.domain.valueobject;

public record MerchantName(String name) {

    public MerchantName {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Merchant name required");
        }
    }
}
