package com.aggregation.api.infrastructure.persistence.entity;

import com.aggregation.api.domain.model.Transaction;
import com.aggregation.api.domain.valueobject.*;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "transactions")
public class TransactionEntity {

    @EmbeddedId
    private TransactionId id;

    @Embedded
    private CustomerId customerId;

    @Embedded
    @AttributeOverride(name = "name", column = @Column(name = "merchant_name"))
    private MerchantName merchant;

    @Embedded
    private TransactionCategory category;

    @Embedded
    private Money money;

    private String description;

    @Embedded
    private TransactionSource source;

    private Instant timestamp;

    public TransactionEntity() {
        // for jpa
    }

    public Transaction toDomain() {
        return Transaction.from(
                id,
                category,
                source,
                customerId,
                money,
                merchant,
                timestamp
        );
    }

    public TransactionEntity fromDomain(Transaction transaction) {
        var newEntity = new TransactionEntity();
        newEntity.id = transaction.transactionId();
        newEntity.customerId = transaction.customerId();
        newEntity.merchant = transaction.merchant();
        newEntity.category = transaction.category();
        newEntity.money = transaction.money();
        newEntity.source = transaction.source();
        return newEntity;
    }
}
