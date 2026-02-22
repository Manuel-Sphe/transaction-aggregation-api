package com.aggregation.api.application.port.in;

import com.aggregation.api.domain.valueobject.Category;

public interface CategorizeTransactionUseCase {

    Category categorize(String merchantName);
}
