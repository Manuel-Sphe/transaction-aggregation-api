package com.aggregation.api.application.port.out;

import com.aggregation.api.domain.model.Transaction;
import com.aggregation.api.domain.valueobject.CustomerId;
import com.aggregation.api.domain.valueobject.TransactionId;

public interface TransactionRepositoryPort {

    void save(Transaction transaction);

    Transaction findById(TransactionId transactionId);

    Transaction findByCustomerId(CustomerId customerId);
}

