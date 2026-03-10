package com.Flashcash.Flashcash.model;

import jakarta.persistence.*;
import lombok.Data;

import java.lang.reflect.Type;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Contact> contacts;


}
