package com.Flashcash.Flashcash.repository;

import com.Flashcash.Flashcash.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionsRepository extends JpaRepository<Transactions,Integer> {
    public List<Transactions> findAllBySenderId(Integer id);
}
