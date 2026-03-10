package com.Flashcash.Flashcash.repository;

import com.Flashcash.Flashcash.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    public Optional<Account> getAccountByUserId(Integer id);
}
