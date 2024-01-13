package com.example.jar.Service;
import com.example.jar.Service.TransactionService;
import com.example.jar.controller.TransactionController;
import com.example.jar.model.Transaction;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @Test
    void getAllTransactions() throws Exception {
    
        when(transactionService.getAllTransactions()).thenReturn(Collections.singletonList(createSampleTransaction()));

            mockMvc.perform(get("/transactions"))
               .andExpect(status().isOk());
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
}
