package com.aggregation.api.domain.model;

import com.aggregation.api.domain.valueobject.*;

import java.time.Instant;

public record Transaction(
        TransactionId transactionId,
        TransactionCategory category,
        TransactionSource source,
        CustomerId customerId,
        Money money,
        MerchantName merchant,
        Instant timestamp

) {

    public static Transaction from(
            TransactionId transactionId,
            TransactionCategory category,
            TransactionSource source,
            CustomerId customerId,
            Money money,
            MerchantName merchant,
            Instant timestamp
    ) {
        return new Transaction(
                transactionId,
                category,
                source,
                customerId,
                money,
                merchant,
                timestamp

        );
    }

}
