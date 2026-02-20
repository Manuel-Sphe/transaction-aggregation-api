package com.aggregation.api.application.port.in;

import com.aggregation.api.domain.model.Transaction;
import com.aggregation.api.domain.valueobject.CustomerId;

import java.util.List;

public interface AggregateTransactionsUseCase {
    List<Transaction> aggregateTransactions(CustomerId customerId);
}
