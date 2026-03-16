package com.Flashcash.Flashcash.repository;

import com.Flashcash.Flashcash.model.Contact;
import com.Flashcash.Flashcash.model.User;
import com.Flashcash.Flashcash.service.dto.UserContactDto;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ContactRepository extends JpaRepository<Contact, Integer> {
    @Query(value = "select u.id, u.email,u.firstName, u.lastName from User u inner join fetch Contact c on c.user2.id = u.id where c.user1.id = ?1")
   public List<Tuple> getContactsById(Integer id);
    @Query(value = "select  case when count(user2) >= 1 then true else false end  from Contact where user1.id = ?1 AND user2.id = ?2 ")
    public Boolean existsUser2inUser1 (Integer user1, Integer user2);
}
