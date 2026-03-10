package com.Flashcash.Flashcash.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Data
@Entity
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private User recipient;
    @ManyToOne
    private User sender;
    private double amount;
    @CreatedDate
    private Date timestamp;
}
