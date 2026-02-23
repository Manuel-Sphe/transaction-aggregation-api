package com.aggregation.api.domain.port;

import com.aggregation.api.domain.model.Transaction;
import com.aggregation.api.domain.valueobject.CustomerId;

import java.util.List;

public interface TransactionRepositoryPort {

    List<Transaction> findAllByCustomerId (CustomerId customerId);

    void saveAll(List<Transaction> transactions);
}

