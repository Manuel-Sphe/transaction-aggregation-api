package com.aggregation.api.domain.service;

import com.aggregation.api.domain.model.CustomerSummary;
import com.aggregation.api.domain.model.Transaction;
import com.aggregation.api.domain.valueobject.CustomerId;
import com.aggregation.api.domain.valueobject.Money;
import com.aggregation.api.domain.valueobject.Category;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AggregationService {

    public CustomerSummary summarize(
            CustomerId customerId,
            List<Transaction> transactions
    ) {

        if (transactions.isEmpty()) {
            return emptySummary(customerId);
        }

        String currency = transactions.getFirst().getMoney().currency();

        Money total = transactions.stream()
                .map(Transaction::getMoney)
                .reduce(new Money(BigDecimal.ZERO, currency), Money::add);

        Map<Category, Money> perCategory =
                calculatePerCategory(transactions);

        int count = transactions.size();

        Money average = new Money(
                total.amount().divide(BigDecimal.valueOf(count)),
                currency
        );

        Money highest = transactions.stream()
                .map(Transaction::getMoney)
                .max(Comparator.comparing(Money::amount))
                .orElseThrow();

        return new CustomerSummary(
                customerId,
                total,
                perCategory,
                count,
                average,
                highest
        );
    }

    private Map<Category, Money> calculatePerCategory(
            List<Transaction> transactions
    ) {

        Map<Category, Money> result = new HashMap<>();

        for (Transaction tx : transactions) {

            result.merge(
                    tx.getCategory(),
                    tx.getMoney(),
                    Money::add
            );
        }

        return result;
    }

    private CustomerSummary emptySummary(CustomerId customerId) {

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