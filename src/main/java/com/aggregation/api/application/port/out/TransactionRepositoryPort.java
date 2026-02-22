package com.aggregation.api.application.port.out;

import com.aggregation.api.domain.model.Transaction;
import com.aggregation.api.domain.valueobject.CustomerId;
import com.aggregation.api.domain.valueobject.TransactionId;

import java.util.List;
import java.util.Optional;

public interface TransactionRepositoryPort {

    void save(Transaction transaction);

    Optional<Transaction> findById(TransactionId transactionId);

    List<Transaction> findAllByCustomerId (CustomerId customerId);
}

