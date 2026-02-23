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
                "28618d55-82ea-4b3f-b4c8-39befa4f3adc",
                "cust-1",
                "ZoneFitness Claremont",
                new BigDecimal("450.00"),
                "ZAR",
                LocalDateTime.of(2026, 1, 2, 10, 15)),
                Transaction.of(
                        "d4a38236-f6e7-4b98-8124-5aba11ccdf72",
                        "cust-1",
                        "Pick n Pay",
                        new BigDecimal("120.50"),
                        "ZAR",
                        LocalDateTime.of(2026, 1, 3, 18, 40)
                ),
                Transaction.of(
                        "35c7c741-4eca-463a-a6c7-5d30773f2b46",
                        "cust-1",
                        "Uber Trip",
                        new BigDecimal("89.99"),
                        "ZAR",
                        LocalDateTime.of(2026, 1, 4, 8, 20)
                ),

                // --- added more mock transactions (more rows + multiple customers) ---
                Transaction.of(
                        "a7e4e0b9-2b2f-4f6a-9fcb-2f7c40d3c85e",
                        "cust-1",
                        "Steers",
                        new BigDecimal("145.70"),
                        "ZAR",
                        LocalDateTime.of(2026, 1, 4, 12, 55)
                ),
                Transaction.of(
                        "c2d0f26c-5be6-4f95-a1c7-87c00df644d1",
                        "cust-1",
                        "Capitec Bank Fees",
                        new BigDecimal("7.50"),
                        "ZAR",
                        LocalDateTime.of(2026, 1, 6, 9, 1)
                ),
                Transaction.of(
                        "5f2c9a0b-7d62-4a34-9461-6d7f69072836",
                        "cust-1",
                        "Mr D Food",
                        new BigDecimal("268.20"),
                        "ZAR",
                        LocalDateTime.of(2026, 1, 7, 19, 33)
                ),
                Transaction.of(
                        "d3b5d11f-1c80-49b7-8c1e-7ac61a6a2e8a",
                        "cust-2",
                        "Uber Eats",
                        new BigDecimal("312.40"),
                        "ZAR",
                        LocalDateTime.of(2026, 1, 6, 20, 10)
                ),
                Transaction.of(
                        "8f7b6312-4e0c-47c1-8bb1-7b7b1e95a1d2",
                        "cust-2",
                        "Engen 1-Stop",
                        new BigDecimal("720.00"),
                        "ZAR",
                        LocalDateTime.of(2026, 1, 8, 6, 58)
                ),
                Transaction.of(
                        "6b71aedd-8a73-4a23-aad5-7f3ad2d9dc57",
                        "cust-2",
                        "Game Store",
                        new BigDecimal("899.99"),
                        "ZAR",
                        LocalDateTime.of(2026, 1, 10, 11, 20)
                ),
                Transaction.of(
                        "c9f03d18-0d8b-4bdf-9d52-0f2c2a4f2b9c",
                        "cust-3",
                        "Flight Centre",
                        new BigDecimal("3499.00"),
                        "ZAR",
                        LocalDateTime.of(2026, 1, 9, 15, 44)
                ),
                Transaction.of(
                        "d2c8f95b-57ef-4e4f-95c6-fd6e0dfec9a1",
                        "cust-3",
                        "Rain Internet",
                        new BigDecimal("499.00"),
                        "ZAR",
                        LocalDateTime.of(2026, 1, 11, 0, 1)
                ),
                Transaction.of(
                        "2fa0a3c9-88e1-4f33-9430-e6f6c7d1a9c5",
                        "cust-3",
                        "Woolworths Cafe",
                        new BigDecimal("76.30"),
                        "ZAR",
                        LocalDateTime.of(2026, 1, 12, 9, 15)
                )
       );
    }


}
