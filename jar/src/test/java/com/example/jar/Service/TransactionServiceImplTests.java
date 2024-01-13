package com.example.jar.Service;


import com.example.jar.model.ExchangeRateResponse;
import com.example.jar.model.Transaction;
import com.example.jar.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Test
    void getAllTransactions() {
        
        when(transactionRepository.findAll()).thenReturn(Collections.singletonList(createSampleTransaction()));

        
        List<Transaction> transactions = transactionService.getAllTransactions();

                assertEquals(1, transactions.size());
        assertEquals("USD", transactions.get(0).getCurrency());
    }

    @Test
    void recordTransaction() {

        when(transactionRepository.save(Mockito.any(Transaction.class))).thenReturn(createSampleTransaction());


        when(restTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(createExchangeRateResponse());

        Transaction transaction = createSampleTransaction();
        Transaction recordedTransaction = transactionService.recordTransaction(transaction);

        assertEquals("INR", recordedTransaction.getCurrency());
    }

    private Transaction createSampleTransaction() {
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setDate(LocalDate.now());
        transaction.setDescription("Sample Transaction");
        transaction.setAmount(BigDecimal.TEN);
        transaction.setCurrency("USD");
        return transaction;
    }

    private ExchangeRateResponse createExchangeRateResponse() {
        ExchangeRateResponse response = new ExchangeRateResponse();
        response.setRates(Collections.singletonMap("INR", BigDecimal.valueOf(75.0)));
        return response;
    }
}
