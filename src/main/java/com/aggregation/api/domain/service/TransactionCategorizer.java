package com.aggregation.api.domain.service;

import com.aggregation.api.domain.valueobject.Category;
import com.aggregation.api.domain.valueobject.Merchant;

import java.util.Comparator;
import java.util.List;

public class TransactionCategorizer {

    private final List<CategorizationRule> rules;

    public TransactionCategorizer(List<CategorizationRule> rules) {
        this.rules = rules.stream()
                .sorted(Comparator.comparingInt(CategorizationRule::priority))
                .toList();
    }

    public Category categorize(Merchant merchant) {

        return rules.stream()
                .filter(rule -> rule.matches(merchant.name()))
                .map(CategorizationRule::category)
                .findFirst()
                .orElse(Category.OTHER);
    }
}
