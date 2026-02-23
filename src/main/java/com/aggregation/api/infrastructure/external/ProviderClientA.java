package com.aggregation.api.infrastructure.external;

import com.aggregation.api.domain.model.Transaction;
import com.aggregation.api.domain.valueobject.CustomerId;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Component("mockA")
public class ProviderClientA {

    public List<Transaction> fetchTransactions(CustomerId customerId) {
        return mockData()
                .stream()
                .filter(transaction -> transaction.getCustomerId().equals(customerId))
                .sorted(Comparator.comparing(Transaction::getTimestamp).reversed())
                .toList();

    }

    private List<Transaction> mockData() {
        return List.of(
                Transaction.of(
                        "22a23bf4-295a-4ba0-ad2d-3ae0751582ac",
                        "cust-1",
                         "ZoneFitness Claremont",
                        new BigDecimal("450.00"),"ZAR",
                        LocalDateTime.of(2026, 1, 2, 10, 15)
                ),
                Transaction.of(
                        "13263abe-5fa2-4d62-8c0e-64fa070ed1fd",
                        "cust-1",
                        "Pick n Pay",
                        new BigDecimal("120.50"), "ZAR",
                        LocalDateTime.of(2026, 1, 3, 18, 40)
                ),
                Transaction.of(
                        "ec001643-9255-4c19-ac80-a5dccfa73c98",
                        "cust-2",
                        "Virgin Active Cavendish",
                        new BigDecimal("799.00"), "ZAR",
                        LocalDateTime.of(2026, 1, 5, 17, 30)
                )
        );
    }
}
