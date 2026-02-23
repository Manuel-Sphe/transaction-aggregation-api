package com.aggregation.api.domain.model;

import com.aggregation.api.domain.valueobject.CustomerId;
import com.aggregation.api.domain.valueobject.Money;
import com.aggregation.api.domain.valueobject.Category;

import java.util.Map;

public record CustomerSummary(
        CustomerId customerId,
        Money totalSpend,
        Map<Category, Money> spendPerCategory,
        int transactionCount,
        Money averageTransaction,
        Money highestTransaction) {
}