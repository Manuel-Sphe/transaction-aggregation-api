package com.aggregation.api.infrastructure.external;

import com.aggregation.api.application.dto.TransactionDTO;
import com.aggregation.api.application.port.out.TransactionProvider;

import java.util.List;

public class BankBProviderAdapter implements TransactionProvider {
    @Override
    public List<TransactionDTO> fetchTransactions() {
        return List.of();
    }
}
