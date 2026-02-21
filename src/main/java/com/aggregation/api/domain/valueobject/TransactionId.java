package com.aggregation.api.domain.valueobject;

import java.util.UUID;

public record TransactionId(UUID id) {

    public TransactionId {
        if (id == null) {
            throw new IllegalArgumentException("Invalid transaction id");
        }
    }

    public TransactionId() {
        this(UUID.randomUUID());
    }
}
