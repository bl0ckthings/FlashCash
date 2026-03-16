package com.Flashcash.Flashcash.repository;

import com.Flashcash.Flashcash.model.Account;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    public Optional<Account> getAccountByUserId(Integer id);
    @Query("select a.iban from Account a where a.user.id = :id")
    public List<String> findAllIbanByUserId(@Param("id") Integer id);

}
