package com.aggregation.api.application.usecase;

import com.aggregation.api.domain.valueobject.CustomerId;

public interface IngestTransactionsUseCase {
    int ingest(CustomerId customerId);
}
