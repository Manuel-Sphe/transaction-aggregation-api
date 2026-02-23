package com.aggregation.api.domain.model;

import com.aggregation.api.domain.valueobject.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public final class Transaction {
    private final TransactionId transactionId;
    private Category category;
    private final CustomerId customerId;
    private final Money money;
    private final Merchant merchant;
    private final LocalDateTime timestamp;

    public Transaction(
            TransactionId transactionId,
            CustomerId customerId,
            Money money,
            Merchant merchant,
            LocalDateTime timestamp

    ) {
        this.transactionId = transactionId;
        this.customerId = customerId;
        this.money = money;
        this.merchant = merchant;
        this.timestamp = timestamp;
    }

    public static Transaction of(
            String transactionId,
            String customerId,
            String merchant,
            BigDecimal amount,
            String currency,
            LocalDateTime timestamp) {

        return new Transaction(
                new TransactionId(UUID.fromString(transactionId)),
                new CustomerId(customerId),
                new Money(amount, currency),
                new Merchant(merchant),
                timestamp
        );
    }
    public static Transaction fromEntity(
            String transactionId,
            String customerId,
            String merchant,
            String category,
            BigDecimal amount,
            String currency,
            LocalDateTime timestamp) {

        Transaction transaction = of(transactionId, customerId, merchant, amount, currency, timestamp);
        transaction.assignCategory(new Category(category));
        return transaction;
    }

    public void assignCategory(Category category) {
        this.category = category;
    }

}
