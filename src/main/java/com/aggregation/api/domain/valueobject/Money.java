package com.aggregation.api.domain.valueobject;

import java.math.BigDecimal;

public record Money(BigDecimal amount, String currency) {

    public Money {
        if (amount == null || currency == null) {
            throw new IllegalArgumentException("Invalid money");
        }
    }

    public Money add(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Currencies mismatch");
        }
        return new Money(this.amount.add(other.amount), this.currency);
    }
}
