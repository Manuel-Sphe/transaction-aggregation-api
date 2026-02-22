package com.aggregation.api.infrastructure.external;

import com.aggregation.api.domain.model.Transaction;
import com.aggregation.api.domain.valueobject.CustomerId;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Component("mockB")
public class ProviderClientB {

    public List<Transaction> fetchTransactions(CustomerId customerId) {
        return mockData().
                stream().
                filter(transaction -> transaction.getCustomerId().equals(customerId))
                .sorted(Comparator.comparing(Transaction::getTimestamp).reversed())
                .toList();

    }

    private List<Transaction> mockData() {
        return List.of(
                Transaction.of(
                        "cust-1",
                        "ZoneFitness Claremont",
                        new BigDecimal("450.00"),
                        "ZAR",
                        LocalDateTime.of(2026, 1, 2, 10, 15)),
                Transaction.of(
                        "cust-1",
                        "Pick n Pay",
                        new BigDecimal("120.50"),
                        "ZAR",
                        LocalDateTime.of(2026, 1, 3, 18, 40)
                ),
                Transaction.of(
                        "cust-1",
                        "Uber Trip",
                        new BigDecimal("89.99"),
                        "ZAR",
                        LocalDateTime.of(2026, 1, 4, 8, 20)
                )
        );
    }


}
