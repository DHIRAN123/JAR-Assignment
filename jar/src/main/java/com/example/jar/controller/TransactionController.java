package com.example.jar.controller;

import com.example.jar.model.Transaction;
import com.example.jar.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @PostMapping("/record")
    public Transaction recordTransaction(@RequestBody Transaction transaction) {
        return transactionService.recordTransaction(transaction);
    }

    @GetMapping("/daily-reports")
    public List<Transaction> getDailyReports() {
        return transactionService.getDailyReports();
    }
}
