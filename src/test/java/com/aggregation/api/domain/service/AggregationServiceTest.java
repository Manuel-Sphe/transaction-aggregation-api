package com.aggregation.api.domain.service;

import com.aggregation.api.domain.model.CustomerSummary;
import com.aggregation.api.domain.model.Transaction;
import com.aggregation.api.domain.valueobject.Category;
import com.aggregation.api.domain.valueobject.CustomerId;
import com.aggregation.api.domain.valueobject.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AggregationServiceTest {

    private final AggregationService service =
            new AggregationService();

    @Test
    void shouldCalculateTotalSpend() {

        CustomerId id = new CustomerId("cust-1");

        List<Transaction> transactions = List.of(
                sample("100", "ZAR", Category.GROCERIES),
                sample("200", "ZAR", Category.GROCERIES)
        );

        CustomerSummary summary =
                service.summarize(id, transactions);

        assertEquals(
                new BigDecimal("300"),
                summary.totalSpend().amount()
        );

    }

    @Test
    void shouldReturnEmptySummaryWhenNoTransactions() {
        CustomerId id = new CustomerId("cust-1");

        CustomerSummary summary = service.summarize(id, List.of());

        assertEquals(id, summary.customerId());
        assertEquals(new Money(BigDecimal.ZERO, "ZAR"), summary.totalSpend());
        assertEquals(0, summary.transactionCount());
        assertEquals(new Money(BigDecimal.ZERO, "ZAR"), summary.averageTransaction());
        assertEquals(new Money(BigDecimal.ZERO, "ZAR"), summary.highestTransaction());
        assertTrue(summary.spendPerCategory().isEmpty());
    }

    @Test
    void shouldCalculateCountAverageAndHighest() {
        CustomerId id = new CustomerId("cust-1");

        List<Transaction> transactions = List.of(
                sample("100", "ZAR", Category.GROCERIES),
                sample("200", "ZAR", Category.FITNESS),
                sample("50", "ZAR", Category.GROCERIES)
        );

        CustomerSummary summary = service.summarize(id, transactions);

        assertEquals(3, summary.transactionCount());
        assertEquals(new Money(new BigDecimal("350"), "ZAR"), summary.totalSpend());
        assertEquals(new Money(new BigDecimal("200"), "ZAR"), summary.highestTransaction());

        // 350 / 3 = 116.666..., rounded to 2 decimals HALF_UP => 116.67
        assertEquals(new Money(new BigDecimal("116.67"), "ZAR"), summary.averageTransaction());
    }

    @Test
    void shouldCalculateSpendPerCategory() {
        CustomerId id = new CustomerId("cust-1");

        List<Transaction> transactions = List.of(
                sample("100", "ZAR", Category.GROCERIES),
                sample("200", "ZAR", Category.GROCERIES),
                sample("50", "ZAR", Category.FITNESS)
        );

        CustomerSummary summary = service.summarize(id, transactions);

        assertEquals(new Money(new BigDecimal("300"), "ZAR"),
                summary.spendPerCategory().get(Category.GROCERIES));
        assertEquals(new Money(new BigDecimal("50"), "ZAR"),
                summary.spendPerCategory().get(Category.FITNESS));
    }

    @Test
    void shouldThrowWhenCurrenciesMismatch() {
        CustomerId id = new CustomerId("cust-1");

        List<Transaction> transactions = List.of(
                sample("100", "ZAR", Category.GROCERIES),
                sample("200", "USD", Category.GROCERIES)
        );

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> service.summarize(id, transactions)
        );

        assertEquals("Currencies mismatch", ex.getMessage());
    }

    @Test
    void shouldGroupUnderNullCategoryWhenCategoryNotAssigned() {
        CustomerId id = new CustomerId("cust-1");

        Transaction tx1 = sample("100", "ZAR", null);
        Transaction tx2 = sample("50", "ZAR", null);

        CustomerSummary summary = service.summarize(id, List.of(tx1, tx2));

        assertTrue(summary.spendPerCategory().containsKey(null));
        assertEquals(new Money(new BigDecimal("150"), "ZAR"), summary.spendPerCategory().get(null));
    }

    @Test
    void shouldRoundAverageWhenAverageIsNonTerminatingDecimal() {
        CustomerId id = new CustomerId("cust-1");

        List<Transaction> transactions = List.of(
                sample("100", "ZAR", Category.GROCERIES),
                sample("0", "ZAR", Category.GROCERIES),
                sample("0", "ZAR", Category.GROCERIES)
        );

        CustomerSummary summary = service.summarize(id, transactions);

        // 100 / 3 = 33.333..., rounded to 2 decimals HALF_UP => 33.33
        assertEquals(new Money(new BigDecimal("33.33"), "ZAR"), summary.averageTransaction());
    }

    private Transaction sample(String amount, String currency, Category category) {
        Transaction tx = Transaction.of(
                UUID.randomUUID().toString(),
                "cust-1",
                "Test Merchant",
                new BigDecimal(amount),
                currency,
                LocalDateTime.now()
        );
        if (category != null) {
            tx.assignCategory(category);
        }
        return tx;
    }

}