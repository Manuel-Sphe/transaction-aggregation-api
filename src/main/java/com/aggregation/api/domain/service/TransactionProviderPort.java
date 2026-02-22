package com.aggregation.api.domain.service;

import com.aggregation.api.domain.model.Transaction;
import com.aggregation.api.domain.valueobject.CustomerId;

import java.util.List;

public interface TransactionProviderPort {
    List<Transaction> fetchTransactions(CustomerId customerId);
}
