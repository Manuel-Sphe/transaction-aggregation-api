package com.aggregation.api.application.service;

import com.aggregation.api.application.usecase.GetCustomerSummaryUseCase;
import com.aggregation.api.domain.port.TransactionRepositoryPort;
import com.aggregation.api.domain.model.CustomerSummary;
import com.aggregation.api.domain.model.Transaction;
import com.aggregation.api.domain.service.AggregationService;
import com.aggregation.api.domain.valueobject.CustomerId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SummaryService implements GetCustomerSummaryUseCase {

    private final TransactionRepositoryPort repository;
    private final AggregationService aggregationService;

    public SummaryService(
            TransactionRepositoryPort repository
    ) {
        this.repository = repository;
        this.aggregationService = new AggregationService();
    }

    @Override
    public CustomerSummary getSummary(CustomerId customerId) {

        List<Transaction> transactions =
                repository.findAllByCustomerId(customerId);

        return aggregationService.summarize(
                customerId,
                transactions
        );
    }
}
