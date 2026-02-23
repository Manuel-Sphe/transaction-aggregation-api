package com.aggregation.api.web;

import com.aggregation.api.application.usecase.GetCustomerMonthlySummaryUseCase;
import com.aggregation.api.application.usecase.GetCustomerSummaryUseCase;
import com.aggregation.api.application.usecase.IngestTransactionsUseCase;
import com.aggregation.api.domain.model.CustomerSummary;
import com.aggregation.api.domain.model.Transaction;
import com.aggregation.api.domain.port.TransactionRepositoryPort;
import com.aggregation.api.domain.valueobject.CustomerId;
import com.aggregation.api.domain.valueobject.Money;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TransactionRepositoryPort repositoryPort;

    @MockitoBean
    private IngestTransactionsUseCase ingestTransactionsUseCase;

    @MockitoBean
    private GetCustomerSummaryUseCase getCustomerSummaryUseCase;

    @MockitoBean
    private GetCustomerMonthlySummaryUseCase getCustomerMonthlySummaryUseCase;

    @Test
    void ingest_shouldReturnMessageAndDelegateToUseCase() throws Exception {
        when(ingestTransactionsUseCase.ingest(any(CustomerId.class))).thenReturn(2);

        mockMvc.perform(post("/customers/{customerId}/transactions/ingest", "cust-1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Ingested 2 transactions"));

        verify(ingestTransactionsUseCase).ingest(new CustomerId("cust-1"));
    }

    @Test
    void getTransactions_shouldReturnOkAndDelegateToRepository() throws Exception {
        Transaction tx = Transaction.of(
                "ec001643-9255-4c19-ac80-a5dccfa73c98",
                "cust-1",
                "Test Merchant",
                new BigDecimal("10.00"),
                "ZAR",
                LocalDateTime.of(2026, 1, 1, 10, 0)
        );

        when(repositoryPort.findAllByCustomerId(new CustomerId("cust-1")))
                .thenReturn(List.of(tx));

        mockMvc.perform(get("/customers/{customerId}/transactions", "cust-1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"));

        verify(repositoryPort).findAllByCustomerId(new CustomerId("cust-1"));
    }

    @Test
    void getSummary_shouldReturnOkAndDelegateToUseCase() throws Exception {
        CustomerSummary summary = new CustomerSummary(
                new CustomerId("cust-1"),
                new Money(new BigDecimal("100.00"), "ZAR"),
                Map.of(),
                1,
                new Money(new BigDecimal("100.00"), "ZAR"),
                new Money(new BigDecimal("100.00"), "ZAR")
        );

        when(getCustomerSummaryUseCase.getSummary(new CustomerId("cust-1")))
                .thenReturn(summary);

        mockMvc.perform(get("/customers/{customerId}/transactions/summary", "cust-1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"));

        verify(getCustomerSummaryUseCase).getSummary(new CustomerId("cust-1"));
    }

    @Test
    void getMonthlySummary_shouldReturnOkAndJsonMap() throws Exception {
        CustomerId customerId = new CustomerId("cust-1");

        Map<YearMonth, CustomerSummary> monthly = new LinkedHashMap<>();
        monthly.put(
                YearMonth.of(2026, 1),
                new CustomerSummary(
                        customerId,
                        new Money(new BigDecimal("100.00"), "ZAR"),
                        Map.of(),
                        1,
                        new Money(new BigDecimal("100.00"), "ZAR"),
                        new Money(new BigDecimal("100.00"), "ZAR")
                )
        );

        when(getCustomerMonthlySummaryUseCase.getMonthlySummary(customerId))
                .thenReturn(monthly);

        mockMvc.perform(get("/customers/{customerId}/transactions/monthly-summary", "cust-1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$.['2026-01']").exists());

        verify(getCustomerMonthlySummaryUseCase).getMonthlySummary(customerId);
    }
}