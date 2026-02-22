package com.aggregation.api.domain.service;

import com.aggregation.api.domain.valueobject.Category;

import java.util.List;

public class DefaultCategorizationRules {

    private DefaultCategorizationRules() {}

    public List<CategorizationRule> defaultRules() {
        return List.of(
                new CategorizationRule("uber|bolt|taxify",
                        Category.TRANSPORT, 1),

                new CategorizationRule("checkers|pick n pay|woolworths",
                        Category.GROCERIES, 2),

                new CategorizationRule("netflix|spotify|showmax",
                        Category.ENTERTAINMENT, 3),

                new CategorizationRule("eskom|telkom|vodacom",
                        Category.BILLS, 4)
        );
    }
}
