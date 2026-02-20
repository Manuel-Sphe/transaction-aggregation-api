package com.aggregation.api.domain.valueobject;

public record MerchantName(String value) {

    public MerchantName {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Merchant name required");
        }
    }
}
