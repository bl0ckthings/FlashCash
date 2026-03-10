package com.Flashcash.Flashcash.controller;


import com.Flashcash.Flashcash.model.User;
import com.Flashcash.Flashcash.repository.AccountRepository;
import com.Flashcash.Flashcash.repository.UserRepository;
import com.Flashcash.Flashcash.service.SessionService;
import com.Flashcash.Flashcash.service.UserService;
import com.Flashcash.Flashcash.service.form.SignUpForm;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;


@Controller
@AllArgsConstructor
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final SessionService sessionService;
    private final AccountRepository accountRepository;

    @GetMapping("auth/signup")
    public ModelAndView showSignUpForm() {
        return new ModelAndView("/auth/signup","signUpForm", new SignUpForm());
    }

    @PostMapping("auth/signup")
    public String processRegistration(@ModelAttribute("signUpForm") @Valid SignUpForm form,
                                            RedirectAttributes redirectAttributes) {
        Optional<User> user = userRepository.findUserByEmail(form.getEmail());

        if (user.isPresent()) {
            redirectAttributes.addFlashAttribute("emailAlreadyExist", true);
            redirectAttributes.addFlashAttribute("signUpForm", form);
            return "redirect:/auth/signup";
        }
        userService.processRegistration(form);
        return "redirect:/auth/signin";
    }

    @GetMapping("/")
    public ModelAndView showHomePage() {
        System.out.println(sessionService.getCurrentUser());

        return new ModelAndView("home");
    }
}
