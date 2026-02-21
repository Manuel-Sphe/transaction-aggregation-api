package com.aggregation.api.application.port.out;

import com.aggregation.api.application.dto.TransactionDTO;

import java.util.List;

public interface TransactionProvider {

    List<TransactionDTO> fetchTransactions();
}
