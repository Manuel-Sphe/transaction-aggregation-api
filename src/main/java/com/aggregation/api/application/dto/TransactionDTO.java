package com.aggregation.api.application.dto;

import java.math.BigDecimal;

public record TransactionDTO(
        String id,
        String customerId,
        BigDecimal amount,
        String currency,
        String description,
        String merchantName,
        String source
) {
}
