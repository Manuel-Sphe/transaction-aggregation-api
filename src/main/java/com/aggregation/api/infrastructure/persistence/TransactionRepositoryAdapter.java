package com.aggregation.api.infrastructure.persistence;

import com.aggregation.api.domain.port.TransactionRepositoryPort;
import com.aggregation.api.domain.model.Transaction;
import com.aggregation.api.domain.valueobject.CustomerId;
import com.aggregation.api.infrastructure.persistence.entity.TransactionEntity;
import com.aggregation.api.infrastructure.persistence.repository.JpaTransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionRepositoryAdapter implements TransactionRepositoryPort {

    private final JpaTransactionRepository transactionRepository;

    public TransactionRepositoryAdapter(JpaTransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> findAllByCustomerId(CustomerId customerId) {
        return transactionRepository.findAllByCustomerId(customerId.customerId())
                .stream().map(TransactionEntity::toDomain)
                .toList();
    }

    @Override
    public void saveAll(List<Transaction> transactions) {
        transactionRepository
                .saveAll(
                        transactions.stream()
                                .map(TransactionEntity::new)
                                .toList()
                );
    }

}
