package com.aggregation.api.application.port.out;

import com.aggregation.api.domain.model.Transaction;
import com.aggregation.api.domain.valueobject.CustomerId;

import java.util.List;

public interface TransactionProviderPort {
    List<Transaction> fetchTransactionsByCustomerId(CustomerId customerId);
}
