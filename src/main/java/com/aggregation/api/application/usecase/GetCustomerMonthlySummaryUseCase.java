package com.aggregation.api.application.usecase;

import com.aggregation.api.domain.model.CustomerSummary;
import com.aggregation.api.domain.valueobject.CustomerId;

import java.time.YearMonth;
import java.util.Map;

public interface GetCustomerMonthlySummaryUseCase {
    Map<YearMonth, CustomerSummary> getMonthlySummary(CustomerId customerId);
}
