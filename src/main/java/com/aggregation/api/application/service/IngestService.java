package com.aggregation.api.application.service;

import com.aggregation.api.application.usecase.IngestTransactionsUseCase;
import com.aggregation.api.domain.port.TransactionProviderPort;
import com.aggregation.api.domain.port.TransactionRepositoryPort;
import com.aggregation.api.domain.model.Transaction;
import com.aggregation.api.domain.service.DefaultCategorizationRules;
import com.aggregation.api.domain.service.TransactionCategorizer;
import com.aggregation.api.domain.valueobject.Category;
import com.aggregation.api.domain.valueobject.CustomerId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngestService implements IngestTransactionsUseCase {

    private final TransactionProviderPort providerPort;
    private final TransactionRepositoryPort repositoryPort;

    private final TransactionCategorizer categorizer;

    @Autowired
    public IngestService(
            TransactionProviderPort providerPort,
            TransactionRepositoryPort repositoryPort
    ) {
        this(providerPort, repositoryPort,
                new TransactionCategorizer(DefaultCategorizationRules.defaultRules()));
    }

    // Intended for tests (or manual wiring) where you want to inject a mock/alternative categorizer
    IngestService(
            TransactionProviderPort providerPort,
            TransactionRepositoryPort repositoryPort,
            TransactionCategorizer categorizer
    ) {
        this.providerPort = providerPort;
        this.repositoryPort = repositoryPort;
        this.categorizer = categorizer;
    }


    @Override
    public int ingest(CustomerId customerId) {
        List<Transaction> transactions =
                providerPort.fetchTransactions(customerId);

        transactions.forEach(tx -> {

            Category category =
                    categorizer.categorize(tx.getMerchant());
            tx.assignCategory(category);
        });

        repositoryPort.saveAll(transactions);

        return transactions.size();

    }

}
