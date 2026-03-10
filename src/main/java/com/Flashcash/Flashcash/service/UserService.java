package com.Flashcash.Flashcash.service;

import com.Flashcash.Flashcash.model.Account;
import com.Flashcash.Flashcash.model.User;
import com.Flashcash.Flashcash.repository.AccountRepository;
import com.Flashcash.Flashcash.repository.UserRepository;
import com.Flashcash.Flashcash.service.form.SignUpForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.accountRepository = accountRepository;
    }

    public void processRegistration(SignUpForm form) {
        User user = new User();
        Account account = new Account();
        account.setUser(user);
        account.setBalance(0.0);
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setEmail(form.getEmail());
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        accountRepository.save(account);
        userRepository.save(user);
    }
}
