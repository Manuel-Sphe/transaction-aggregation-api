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
                        new BigDecimal("450.00"), "ZAR",
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
                ),

                // --- added more mock transactions (variety across customers/merchants/dates) ---
                Transaction.of(
                        "70fcd3cd-4c4d-4d1c-86d7-0e45e24f3a4b",
                        "cust-1",
                        "Woolworths",
                        new BigDecimal("356.80"), "ZAR",
                        LocalDateTime.of(2026, 1, 6, 13, 5)
                ),
                Transaction.of(
                        "2db4c7c2-9113-4f41-bc83-0c7a0a6a9150",
                        "cust-1",
                        "Netflix",
                        new BigDecimal("199.00"), "ZAR",
                        LocalDateTime.of(2026, 1, 7, 0, 2)
                ),
                Transaction.of(
                        "5a3d9a3e-3ee8-4f2d-ae95-15a7f1d7c9fb",
                        "cust-1",
                        "Shell Garage",
                        new BigDecimal("650.00"), "ZAR",
                        LocalDateTime.of(2026, 1, 8, 7, 42)
                ),
                Transaction.of(
                        "e6a7f4c0-9cdb-4b67-9302-7b77e10ef3e2",
                        "cust-2",
                        "Dis-Chem",
                        new BigDecimal("238.40"), "ZAR",
                        LocalDateTime.of(2026, 1, 9, 16, 12)
                ),
                Transaction.of(
                        "f0c8c6de-2c59-4b4b-bd06-dca6f2d83c14",
                        "cust-2",
                        "Checkers",
                        new BigDecimal("512.35"), "ZAR",
                        LocalDateTime.of(2026, 1, 11, 12, 25)
                ),
                Transaction.of(
                        "8a1a4a83-2a6d-4a66-91ce-cc7a1d0d3c2a",
                        "cust-2",
                        "Spotify",
                        new BigDecimal("64.99"), "ZAR",
                        LocalDateTime.of(2026, 1, 12, 0, 5)
                ),
                Transaction.of(
                        "1b5e7b9f-6a35-4db7-9f4c-52e679d6f10f",
                        "cust-3",
                        "Takealot",
                        new BigDecimal("1299.00"), "ZAR",
                        LocalDateTime.of(2026, 1, 12, 19, 10)
                ),
                Transaction.of(
                        "9e5f1c45-74d1-4dd7-b5b4-3a5f5c91c7d8",
                        "cust-3",
                        "KFC",
                        new BigDecimal("98.50"), "ZAR",
                        LocalDateTime.of(2026, 1, 13, 20, 45)
                ),
                Transaction.of(
                        "3f9f1ce0-51aa-4bd3-8d72-6c2d38fe1a93",
                        "cust-3",
                        "MyCiti Bus",
                        new BigDecimal("25.00"), "ZAR",
                        LocalDateTime.of(2026, 1, 14, 8, 5)
                ),
                Transaction.of(
                        "b624bb69-44be-43b4-9b2c-7fe7b1c9f4a1",
                        "cust-3",
                        "Clicks",
                        new BigDecimal("179.90"), "ZAR",
                        LocalDateTime.of(2026, 1, 15, 14, 18)
                )
        );
    }
}
