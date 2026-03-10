package com.Flashcash.Flashcash.repository;

import com.Flashcash.Flashcash.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
}
