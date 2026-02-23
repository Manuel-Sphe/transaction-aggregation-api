package com.aggregation.api.domain.service;

import com.aggregation.api.domain.valueobject.Category;

import java.util.regex.Pattern;

public class CategorizationRule {

    private final Pattern merchantPattern;
    private final Category category;
    private final int priority;

    public CategorizationRule(String regex,
                              Category category,
                              int priority) {
        this.merchantPattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        this.category = category;
        this.priority = priority;
    }

    public boolean matches(String merchant) {
        return merchant != null &&
                merchantPattern.matcher(merchant).find();
    }

    public Category category() {
        return category;
    }

    public int priority() {
        return priority;
    }
}
