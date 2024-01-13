package com.example.jar.Service;

import com.example.jar.model.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> getAllTransactions();

    Transaction saveTransaction(Transaction transaction);

    Transaction recordTransaction(Transaction transaction);

    List<Transaction> getDailyReports();
}
