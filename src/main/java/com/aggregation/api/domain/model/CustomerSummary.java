package com.aggregation.api.domain.model;

import com.aggregation.api.domain.valueobject.CustomerId;
import com.aggregation.api.domain.valueobject.Money;
import com.aggregation.api.domain.valueobject.Category;

import java.math.BigDecimal;
import java.util.Map;

public record CustomerSummary(
        CustomerId customerId,
        Money totalSpend,
        Map<Category, Money> spendPerCategory,
        int transactionCount,
        Money averageTransaction,
        Money highestTransaction) {

    public static CustomerSummary emptySummary(CustomerId customerId) {

        Money zero = new Money(BigDecimal.ZERO, "ZAR");

        return new CustomerSummary(
                customerId,
                zero,
                Map.of(),
                0,
                zero,
                zero
        );
    }
}