package com.aggregation.api.infrastructure.persistence;

import com.aggregation.api.application.port.out.TransactionRepositoryPort;
import com.aggregation.api.domain.model.Transaction;
import com.aggregation.api.domain.valueobject.CustomerId;
import com.aggregation.api.domain.valueobject.TransactionId;
import org.springframework.stereotype.Service;

@Service
public class TransactionRepositoryAdapter implements TransactionRepositoryPort {


    @Override
    public void save(Transaction transaction) {

    }

    @Override
    public Transaction findById(TransactionId transactionId) {
        return null;
    }

    @Override
    public Transaction findByCustomerId(CustomerId customerId) {
        return null;
    }
}
