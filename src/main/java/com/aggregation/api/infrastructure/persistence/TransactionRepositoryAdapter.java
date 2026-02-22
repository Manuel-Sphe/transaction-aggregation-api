package com.aggregation.api.infrastructure.persistence;

import com.aggregation.api.application.port.out.TransactionRepositoryPort;
import com.aggregation.api.domain.model.Transaction;
import com.aggregation.api.domain.valueobject.CustomerId;
import com.aggregation.api.domain.valueobject.TransactionId;
import com.aggregation.api.infrastructure.persistence.entity.TransactionEntity;
import com.aggregation.api.infrastructure.persistence.repository.JpaTransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionRepositoryAdapter implements TransactionRepositoryPort {

    private final JpaTransactionRepository transactionRepository;

    public TransactionRepositoryAdapter(JpaTransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void save(Transaction transaction) {
        var entity = new TransactionEntity(transaction);
        transactionRepository.save(entity);
    }

    @Override
    public Optional<Transaction> findById(TransactionId transactionId) {
        return transactionRepository.findById(transactionId.id())
                .map(TransactionEntity::toDomain);
    }

    @Override
    public List<Transaction> findAllByCustomerId(CustomerId customerId) {
        return transactionRepository.findAllByCustomerId(customerId.customerId())
                .stream().map(TransactionEntity::toDomain)
                .toList();
    }

}
