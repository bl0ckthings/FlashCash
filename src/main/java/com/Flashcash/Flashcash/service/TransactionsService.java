package com.Flashcash.Flashcash.service;

import com.Flashcash.Flashcash.model.Account;
import com.Flashcash.Flashcash.model.Transactions;
import com.Flashcash.Flashcash.model.User;
import com.Flashcash.Flashcash.repository.AccountRepository;
import com.Flashcash.Flashcash.repository.TransactionsRepository;
import com.Flashcash.Flashcash.repository.UserRepository;
import com.Flashcash.Flashcash.service.form.TransferForm;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Service
@AllArgsConstructor

public class TransactionsService {

        private final TransactionsRepository transactionsRepository;
        private final SessionService sessionService;
        private final UserService userService;
        private final AccountRepository accountRepository;
        private final UserRepository userRepository;

        @Transactional
        public void TransferCash(TransferForm transferForm) throws Exception {
            Account senderAccount = userService.getUserAccount();
            if (senderAccount.getBalance().compareTo(transferForm.getAmount()) > 0) {
                BigDecimal tax = transferForm.getAmount().multiply(new BigDecimal("0.005"));
                BigDecimal total = transferForm.getAmount().add(tax);
                senderAccount.decreaseBalance(total);
                accountRepository.save(senderAccount);

                User recipientUser = userRepository.findUserByEmail(transferForm.getRecipientEmail()).orElseThrow(Exception::new);
                Account recipientAccount = accountRepository.getAccountByUserId(recipientUser.getId()).get();
                recipientAccount.increaseBalance(transferForm.getAmount());
                accountRepository.save(recipientAccount);

                Transactions tx = new Transactions(
                        recipientUser,
                        sessionService.getCurrentUser(),
                        transferForm.getAmount(),
                        LocalDateTime.now()
                );
                transactionsRepository.save(tx);
            }

        }
}
