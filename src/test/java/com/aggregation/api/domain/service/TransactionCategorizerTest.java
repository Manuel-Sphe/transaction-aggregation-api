package com.aggregation.api.domain.service;

import com.aggregation.api.domain.valueobject.Category;
import com.aggregation.api.domain.valueobject.Merchant;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionCategorizerTest {

    private final TransactionCategorizer categorizer =
            new TransactionCategorizer(DefaultCategorizationRules.defaultRules());

    @Test
    void shouldCategorizeUberAsTransport() {
        Category category =
                categorizer.categorize(new Merchant("Uber Ride"));

        assertEquals(Category.TRANSPORT, category);
    }

    @Test
    void shouldCategorizeOtherAsOther() {
        Category category =
                categorizer.categorize(new Merchant("Unknown Merchant"));
    }


}