
package com.example.jar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jar.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
