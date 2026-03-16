package com.Flashcash.Flashcash.service;

import com.Flashcash.Flashcash.model.Account;
import com.Flashcash.Flashcash.model.User;
import com.Flashcash.Flashcash.repository.AccountRepository;
import com.Flashcash.Flashcash.repository.UserRepository;
import com.Flashcash.Flashcash.service.form.SignUpForm;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final SessionService sessionService;



    public void processRegistration(SignUpForm form) {
        User user = new User();
        Account account = new Account();
        account.setUser(user);
        account.setBalance(new BigDecimal("0.0"));
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setEmail(form.getEmail());
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        accountRepository.save(account);
        userRepository.save(user);
    }

    public Account getUserAccount() {

        return accountRepository.getAccountByUserId(sessionService.getCurrentUser().getId()).get();
    }

}
