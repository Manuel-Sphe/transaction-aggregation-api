package com.aggregation.api.infrastructure.external;

import com.aggregation.api.domain.port.TransactionProviderPort;
import com.aggregation.api.domain.model.Transaction;
import com.aggregation.api.domain.valueobject.CustomerId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
public class ExternalTransactionProviderAdapter implements TransactionProviderPort {

    private final ProviderClientA providerA;
    private final ProviderClientB providerB;

    public ExternalTransactionProviderAdapter(ProviderClientA providerA, ProviderClientB providerB) {
        this.providerA = providerA;
        this.providerB = providerB;
    }

    @Override
    public List<Transaction> fetchTransactions(CustomerId customerId) {
        return Stream.concat(
                providerA.fetchTransactions(customerId).stream(),
                        providerB.fetchTransactions(customerId).stream()
                )
                .toList();
    }


}
