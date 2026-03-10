package com.Flashcash.Flashcash.repository;

import com.Flashcash.Flashcash.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepository extends JpaRepository<Transactions,Integer> {
}
