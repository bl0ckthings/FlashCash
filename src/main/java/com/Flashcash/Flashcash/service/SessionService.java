package com.Flashcash.Flashcash.service;

import com.Flashcash.Flashcash.model.Account;
import com.Flashcash.Flashcash.model.User;
import com.Flashcash.Flashcash.repository.AccountRepository;
import com.Flashcash.Flashcash.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SessionService {
        private final UserRepository userRepository;
        private final AccountRepository accountRepository;


    public User getCurrentUser() {
       org.springframework.security.core.userdetails.User userDetails = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         return userRepository
                .findUserByEmail(userDetails.getUsername()).get();
    }

    public Account getUserAccount() {
        return accountRepository.getAccountByUserId(getCurrentUser().getId()).get();
    }
}
