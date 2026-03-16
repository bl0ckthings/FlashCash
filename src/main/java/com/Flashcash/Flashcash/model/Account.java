package com.Flashcash.Flashcash.model;

import com.Flashcash.Flashcash.service.exceptions.InsufficientFundsException;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private BigDecimal balance;
    @ElementCollection
    @CollectionTable(name = "account_iban", joinColumns = @JoinColumn(name = "account_id"))
    @Column(name = "iban")
    private List<String> iban;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;



    public List<String> setListIban(String iban) {
        if (iban != null) {
            this.iban.add(iban);
        }
         return this.iban;
    }
    public Account increaseBalance(BigDecimal amount) {
        this.balance = this.balance.add(amount);
        return this;
    }

    public Account decreaseBalance(BigDecimal amount) {
        if (balance.compareTo(amount) > 0) {
            throw new InsufficientFundsException("Not enough fund for withdrawal or transfer");
        }
        this.balance = this.balance.subtract(amount);
        return this;
    }
}
