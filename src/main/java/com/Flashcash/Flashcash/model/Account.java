package com.Flashcash.Flashcash.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double balance;
    private String iban1;
    private String iban2;
    private String iban3;
    private String iban4;
    private String iban5;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;

}
