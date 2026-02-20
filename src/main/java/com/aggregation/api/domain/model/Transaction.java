package com.aggregation.api.domain.model;

import com.aggregation.api.domain.valueobject.CustomerId;
import com.aggregation.api.domain.valueobject.MerchantName;
import com.aggregation.api.domain.valueobject.Money;
import com.aggregation.api.domain.valueobject.TransactionCategory;

public class Transaction {

    private String id;
    private CustomerId customerId;
    private Money money;
    private MerchantName merchant;
    private TransactionCategory category;
}
