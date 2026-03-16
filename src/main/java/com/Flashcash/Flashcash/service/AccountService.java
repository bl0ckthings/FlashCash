package com.Flashcash.Flashcash.service;

import com.Flashcash.Flashcash.model.Account;
import com.Flashcash.Flashcash.repository.AccountRepository;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserService userService;

    public void addCashToAccount(BigDecimal amount) {
        Account accountUser = userService.getUserAccount();
        accountUser.increaseBalance(amount);
        accountRepository.save(accountUser);
    }

    public void withdrawCashToBankAccount(BigDecimal amount) {

        Account userAccount = userService.getUserAccount();
        userAccount.decreaseBalance(amount);
        accountRepository.save(userAccount);
    }

    public void addIban(String iban) {
        Account userAccount  = userService.getUserAccount();
        if (userAccount.getIban() == null) {
            userAccount.setIban(new ArrayList<>());
        }
        if (!userAccount.getIban().contains(iban)) {
            userAccount.getIban().add(iban);
        }

       accountRepository.save(userAccount);
    }
}
