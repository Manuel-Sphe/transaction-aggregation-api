package com.aggregation.api.web;

import com.aggregation.api.application.usecase.GetCustomerSummaryUseCase;
import com.aggregation.api.application.usecase.IngestTransactionsUseCase;
import com.aggregation.api.domain.model.CustomerSummary;
import com.aggregation.api.domain.model.Transaction;
import com.aggregation.api.domain.port.TransactionRepositoryPort;
import com.aggregation.api.domain.valueobject.CustomerId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers/{customerId}/transactions")
public class TransactionController {

    private final TransactionRepositoryPort repositoryPort;
    private final IngestTransactionsUseCase ingestTransactionsUseCase;
    private final GetCustomerSummaryUseCase getCustomerSummaryUseCase;

    public TransactionController(
            TransactionRepositoryPort repositoryPort,
            IngestTransactionsUseCase ingestTransactionsUseCase,
            GetCustomerSummaryUseCase getCustomerSummaryUseCase) {
        this.repositoryPort = repositoryPort;
        this.ingestTransactionsUseCase = ingestTransactionsUseCase;
        this.getCustomerSummaryUseCase = getCustomerSummaryUseCase;
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
}
