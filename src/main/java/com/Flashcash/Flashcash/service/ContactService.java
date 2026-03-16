package com.Flashcash.Flashcash.service;

import com.Flashcash.Flashcash.model.Contact;
import com.Flashcash.Flashcash.model.User;
import com.Flashcash.Flashcash.repository.ContactRepository;
import com.Flashcash.Flashcash.repository.UserRepository;
import com.Flashcash.Flashcash.service.dto.DtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ContactService {

    private final SessionService sessionService;
    private final UserRepository userRepository;
    private final ContactRepository contactRepository;

    public void addContact(String email) throws Exception {
        User user1 = sessionService.getCurrentUser();
        Contact contact = new Contact();
        User user2 = userRepository.findUserByEmail(email).get();
        if (!contactRepository.existsUser2inUser1(user1.getId(), user2.getId())) {
            contact.setUser1(user1);
            contact.setUser2(user2);
            contactRepository.save(contact);

        } else {
            throw new Exception("User already in contacts");
        }
    }


}
