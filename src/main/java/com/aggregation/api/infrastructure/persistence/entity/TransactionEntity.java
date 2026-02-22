package com.aggregation.api.infrastructure.persistence.entity;

import com.aggregation.api.domain.model.Transaction;
import com.aggregation.api.domain.valueobject.*;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public class TransactionEntity {

    @Id
    private UUID id;

    private String customerId;

    private String merchant;

    private String category;

    private BigDecimal amount;

    private String currency;

    private LocalDateTime timestamp;

    public TransactionEntity() {
        // for jpa
    }

    public Transaction toDomain() {
        return Transaction.fromEntity(
                customerId,
                category,
                merchant,
                amount,
                currency,
                timestamp
        );
    }

    public TransactionEntity(Transaction transaction) {
        this.id = transaction.getTransactionId().id();
        this.customerId = transaction.getCustomerId().customerId();
        this.merchant = transaction.getMerchant().name();
        this.category = transaction.getCategory().category();
        this.amount = transaction.getMoney().amount();
        this.currency = transaction.getMoney().currency();
        this.timestamp = transaction.getTimestamp();
    }
}
