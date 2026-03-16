package com.Flashcash.Flashcash.controller;

import com.Flashcash.Flashcash.repository.ContactRepository;
import com.Flashcash.Flashcash.service.ContactService;
import com.Flashcash.Flashcash.service.SessionService;
import com.Flashcash.Flashcash.service.dto.UserContactDto;
import com.Flashcash.Flashcash.service.dto.DtoMapper;
import com.Flashcash.Flashcash.service.form.EmailForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@AllArgsConstructor
public class ContactController {

        private final SessionService sessionService;
        private final ContactService contactService;
        private final ContactRepository contactRepository;
        private final DtoMapper dtoMapper;

    @GetMapping("/app/contact")
    public ModelAndView showContactPage(Model model) {
        List<UserContactDto> listContacts = dtoMapper.getListOfContacts();

        model.addAttribute("emailForm", new EmailForm());
     return new ModelAndView("/app/contact", "contacts", listContacts);
    }

    @PostMapping("/app/contact")
    public  String addContact(@ModelAttribute("emailForm")EmailForm emailForm) {
        try {
            contactService.addContact(emailForm.getEmail());
        } catch (Exception e) {

            System.out.println("User already in list of contact");
        }

        return "redirect:/app/contact";
    }





}
