package com.Flashcash.Flashcash.model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private User user1;
    @ManyToOne
    private User user2;
}
