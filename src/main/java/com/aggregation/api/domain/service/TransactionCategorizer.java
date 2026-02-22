package com.aggregation.api.domain.service;

import com.aggregation.api.application.port.in.CategorizeTransactionUseCase;
import com.aggregation.api.domain.valueobject.Category;

import java.util.Comparator;
import java.util.List;

public class TransactionCategorizer implements CategorizeTransactionUseCase {

    private final List<CategorizationRule> rules;

    public TransactionCategorizer(List<CategorizationRule> rules) {
        this.rules = rules.stream()
                .sorted(Comparator.comparingInt(CategorizationRule::priority))
                .toList();
    }

    @Override
    public Category categorize(String merchant) {

        return rules.stream()
                .filter(rule -> rule.matches(merchant))
                .map(CategorizationRule::category)
                .findFirst()
                .orElse(Category.OTHER);
    }
}
