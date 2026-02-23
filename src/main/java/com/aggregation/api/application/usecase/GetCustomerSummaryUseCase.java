package com.aggregation.api.application.usecase;

import com.aggregation.api.domain.model.CustomerSummary;
import com.aggregation.api.domain.valueobject.CustomerId;

public interface GetCustomerSummaryUseCase {
    CustomerSummary getSummary(CustomerId customerId);
}
