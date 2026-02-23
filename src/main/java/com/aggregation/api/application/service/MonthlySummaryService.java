package com.aggregation.api.application.service;

import com.aggregation.api.application.usecase.GetCustomerMonthlySummaryUseCase;
import com.aggregation.api.domain.model.CustomerSummary;
import com.aggregation.api.domain.model.Transaction;
import com.aggregation.api.domain.port.TransactionRepositoryPort;
import com.aggregation.api.domain.service.AggregationService;
import com.aggregation.api.domain.valueobject.CustomerId;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MonthlySummaryService implements GetCustomerMonthlySummaryUseCase {

    private final AggregationService aggregationService;
    private final TransactionRepositoryPort repository;


    public MonthlySummaryService(TransactionRepositoryPort repository) {
        this.aggregationService = new AggregationService();
        this.repository = repository;
    }


    @Override
    public Map<YearMonth, CustomerSummary> getMonthlySummary(CustomerId customerId) {

        List<Transaction> transactions = repository.findAllByCustomerId(customerId);
        Map<YearMonth, List<Transaction>> byMonth =
                transactions.stream().collect(Collectors.groupingBy(tx -> YearMonth.from(tx.getTimestamp())));

        return byMonth.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.naturalOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> aggregationService.summarize(customerId, entry.getValue()),
                        (a, b) -> a,
                        LinkedHashMap::new
                        ));
    }
}
