package com.aggregation.api.web;

import com.aggregation.api.application.usecase.GetCustomerMonthlySummaryUseCase;
import com.aggregation.api.application.usecase.GetCustomerSummaryUseCase;
import com.aggregation.api.application.usecase.IngestTransactionsUseCase;
import com.aggregation.api.domain.model.CustomerSummary;
import com.aggregation.api.domain.model.Transaction;
import com.aggregation.api.domain.port.TransactionRepositoryPort;
import com.aggregation.api.domain.valueobject.CustomerId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers/{customerId}/transactions")
public class TransactionController {

    private final TransactionRepositoryPort repositoryPort;
    private final IngestTransactionsUseCase ingestTransactionsUseCase;
    private final GetCustomerSummaryUseCase getCustomerSummaryUseCase;
    private final GetCustomerMonthlySummaryUseCase getCustomerMonthlySummaryUseCase;

    public TransactionController(
            TransactionRepositoryPort repositoryPort,
            IngestTransactionsUseCase ingestTransactionsUseCase,
            GetCustomerSummaryUseCase getCustomerSummaryUseCase,
            GetCustomerMonthlySummaryUseCase getCustomerMonthlySummaryUseCase) {
        this.repositoryPort = repositoryPort;
        this.ingestTransactionsUseCase = ingestTransactionsUseCase;
        this.getCustomerSummaryUseCase = getCustomerSummaryUseCase;
        this.getCustomerMonthlySummaryUseCase = getCustomerMonthlySummaryUseCase;
    }

    @PostMapping("/ingest")
    public ResponseEntity<String> ingest(@PathVariable String customerId) {
        int saved = ingestTransactionsUseCase.ingest(new CustomerId(customerId));
        return ResponseEntity.ok("Ingested " + saved + " transactions");
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getTransactions(
            @PathVariable String customerId
    ) {
        List<Transaction> response =
                repositoryPort.findAllByCustomerId(new CustomerId(customerId));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/summary")
    public ResponseEntity<CustomerSummary> getSummary(
            @PathVariable String customerId
    ) {
        CustomerSummary response =
                getCustomerSummaryUseCase.getSummary(new CustomerId(customerId));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/monthly-summary")
    public ResponseEntity<Map<String, CustomerSummary>> getMonthlySummary(
            @PathVariable String customerId
    ) {
        Map<YearMonth, CustomerSummary> monthly =
                getCustomerMonthlySummaryUseCase.getMonthlySummary(new CustomerId(customerId));

        // Return stable JSON keys ("2026-01") instead of YearMonth objects as map keys
        Map<String, CustomerSummary> response = monthly.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> e.getKey().toString(),
                        Map.Entry::getValue,
                        (a, b) -> a,
                        java.util.LinkedHashMap::new
                ));

        return ResponseEntity.ok(response);
    }

}
