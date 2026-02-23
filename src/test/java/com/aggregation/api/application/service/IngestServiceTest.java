package com.aggregation.api.application.service;

import com.aggregation.api.domain.model.Transaction;
import com.aggregation.api.domain.port.TransactionProviderPort;
import com.aggregation.api.domain.port.TransactionRepositoryPort;
import com.aggregation.api.domain.service.TransactionCategorizer;
import com.aggregation.api.domain.valueobject.Category;
import com.aggregation.api.domain.valueobject.CustomerId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IngestServiceTest {

    @Mock
    private TransactionProviderPort providerPort;

    @Mock
    private TransactionRepositoryPort repositoryPort;

    @Mock
    private TransactionCategorizer categorizer ;

    @InjectMocks
    private IngestService ingestService;

    @Test
    void shouldFetchCategorizeAndPersist() {

        CustomerId customerId = new CustomerId("123");
        Transaction tx = Transaction.of(
                "ec001643-9255-4c19-ac80-a5dccfa73c98",
                "123",
                "Virgin Active Cavendish",
                new BigDecimal("799.00"), "ZAR",
                LocalDateTime.of(2026, 1, 5, 17, 30)
        );


        when(providerPort.fetchTransactions(customerId))
                .thenReturn(List.of(tx));

        when(categorizer.categorize(tx.getMerchant()))
                .thenReturn(Category.FITNESS);

        ingestService.ingest(customerId);

        verify(providerPort).fetchTransactions(customerId);
        verify(categorizer).categorize(tx.getMerchant());
        verify(repositoryPort).saveAll(any());
    }

}