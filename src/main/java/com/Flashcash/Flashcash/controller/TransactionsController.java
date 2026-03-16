package com.Flashcash.Flashcash.controller;

import com.Flashcash.Flashcash.repository.TransactionsRepository;
import com.Flashcash.Flashcash.service.SessionService;
import com.Flashcash.Flashcash.service.TransactionsService;
import com.Flashcash.Flashcash.service.UserService;
import com.Flashcash.Flashcash.service.dto.DtoMapper;
import com.Flashcash.Flashcash.service.dto.UserContactDto;
import com.Flashcash.Flashcash.service.form.TransferForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;

@Controller
@AllArgsConstructor
public class TransactionsController {

    private final TransactionsService transactionsService;
    private final DtoMapper dtoMapper;
    private final TransactionsRepository transactionsRepository;
    private final SessionService sessionService;
    private final UserService userService;
    @GetMapping("app/transfer")
    public ModelAndView showTransferPage(Model model) {
        List<UserContactDto> listContacts = dtoMapper.getListOfContacts();
        BigDecimal balance = userService.getUserAccount().getBalance();
        if (balance != null) {
            model.addAttribute("balance", userService.getUserAccount().getBalance());

        }
        model.addAttribute("contacts", listContacts);
        model.addAttribute("transactions", transactionsRepository.findAllBySenderId(sessionService.getCurrentUser().getId()));
        return new ModelAndView("/app/transfer", "transferForm", new TransferForm());
    }

    @PostMapping("app/transfer")
    public String transferCash(@ModelAttribute("TransferForm") TransferForm transferForm) {

        try {

            transactionsService.TransferCash(transferForm);
        } catch (Exception e) {
            System.out.println("USER NOT FOUND");
        }

        return "redirect:/app/transfer";
    }

}
