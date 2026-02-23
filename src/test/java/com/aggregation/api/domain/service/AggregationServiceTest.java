package com.aggregation.api.domain.service;

import com.aggregation.api.domain.model.CustomerSummary;
import com.aggregation.api.domain.model.Transaction;
import com.aggregation.api.domain.valueobject.CustomerId;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AggregationServiceTest {

    private final AggregationService service =
            new AggregationService();

    @Test
    void shouldCalculateTotalSpend() {

        CustomerId id = new CustomerId("cust-1");

        List<Transaction> transactions = List.of(
                sample("100"),
                sample("200")
        );

        CustomerSummary summary =
                service.summarize(id, transactions);

        assertEquals(
                new BigDecimal("300"),
                summary.totalSpend().amount()
        );

    }

    private Transaction sample(String amount) {
        return Transaction.of(
                UUID.randomUUID().toString(),
                "cust-1",
                "Test Merchant",
                new BigDecimal(amount),
                "ZAR",
                LocalDateTime.now()

        );
    }

}