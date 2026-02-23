package com.aggregation.api.domain.service;

import com.aggregation.api.domain.model.CustomerSummary;
import com.aggregation.api.domain.model.Transaction;
import com.aggregation.api.domain.valueobject.CustomerId;
import com.aggregation.api.domain.valueobject.Money;
import com.aggregation.api.domain.valueobject.Category;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
            return CustomerSummary.emptySummary(customerId);
        }

        String currency = transactions.getFirst().getMoney().currency();

        // Ensure we never aggregate it across currencies
        boolean mismatch = transactions.stream()
                .map(tx -> tx.getMoney().currency())
                .anyMatch(txCurrency -> !currency.equals(txCurrency));
        if (mismatch) {
            throw new IllegalArgumentException("Currencies mismatch");
        }

        Money total = transactions.stream()
                .map(Transaction::getMoney)
                .reduce(new Money(BigDecimal.ZERO, currency), Money::add);

        Map<Category, Money> perCategory =
                calculatePerCategory(transactions);

        int count = transactions.size();

        // Average can be a non-terminating decimal (e.g. 350 / 3), so apply a rounding policy.
        BigDecimal averageAmount = total.amount()
                .divide(BigDecimal.valueOf(count), 2, RoundingMode.HALF_UP);

        Money average = new Money(
                averageAmount,
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

}