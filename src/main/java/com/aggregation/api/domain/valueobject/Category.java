package com.aggregation.api.domain.valueobject;

public record Category(String category) {

    public static final Category TRANSPORT =
            new Category("TRANSPORT");

    public static final Category GROCERIES =
            new Category("GROCERIES");

    public static final Category ENTERTAINMENT =
            new Category("ENTERTAINMENT");

    public static final Category BILLS =
            new Category("BILLS");

    public static final Category FITNESS =
            new Category("FITNESS");

    public static final Category OTHER =
            new Category("OTHER");

    public Category {
        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException("Invalid transaction category");
        }
    }
}
