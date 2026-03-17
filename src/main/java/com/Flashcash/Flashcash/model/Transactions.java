package com.Flashcash.Flashcash.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private User recipient;
    @ManyToOne
    private User sender;
    private BigDecimal amount;
    @CreatedDate
    private LocalDateTime timestamp;

    public Transactions(User recipient, User sender, BigDecimal amount ,LocalDateTime timestamp) {
        this.recipient = recipient;
        this.sender = sender;
        this.amount = amount;
        this.timestamp = timestamp;

    }


}
