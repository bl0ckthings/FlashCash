package com.Flashcash.Flashcash.controller;

import com.Flashcash.Flashcash.model.Account;
import com.Flashcash.Flashcash.repository.AccountRepository;
import com.Flashcash.Flashcash.service.AccountService;
import com.Flashcash.Flashcash.service.SessionService;
import com.Flashcash.Flashcash.service.UserService;
import com.Flashcash.Flashcash.service.form.AddCashForm;
import com.Flashcash.Flashcash.service.form.IbanForm;
import jakarta.persistence.Tuple;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;

@Controller
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final UserService userService;
    private final SessionService sessionService;


    @GetMapping("/app/add-cash")
    public ModelAndView showAddForm(Model model) {
        model.addAttribute("addCashForm", new AddCashForm());
        return new ModelAndView("/app/add-cash");
    }
    @PostMapping("/app/add-cash")
    public String addCash(@ModelAttribute("addCashForm") @Valid AddCashForm addCashForm) {
        accountService.addCashToAccount(addCashForm.getAmount());
        return "redirect:/";
    }
    @PostMapping("/app/withdraw-cash")
    public String withdrawCash(@RequestParam("amountToWithdraw") BigDecimal amountToWithdraw) {
        accountService.withdrawCashToBankAccount(amountToWithdraw);
        return "redirect:/";
    }
    @GetMapping("/app/add-iban")
    public ModelAndView showIbanForm(Model model) {
        List<String> ibans = accountRepository.findAllIbanByUserId(sessionService.getCurrentUser().getId());
      model.addAttribute("ibans", ibans);
        return new ModelAndView("/app/add-iban", "ibanForm", new IbanForm());
    }



    @PostMapping("/app/add-iban")
    public String addIban(@ModelAttribute("addIbanForm") @Valid IbanForm ibanForm) {
        try {
            accountService.addIban(ibanForm.getIban());
        } catch (IllegalStateException e) {
            System.out.println("Maximum number of IBANs reached");
        }
        return "redirect:/app/add-iban";
    }






}
