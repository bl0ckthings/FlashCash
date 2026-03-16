package com.Flashcash.Flashcash.controller;

import com.Flashcash.Flashcash.service.SessionService;
import com.Flashcash.Flashcash.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

@Controller
@AllArgsConstructor

public class HomeController {
    private final UserService userService;
    private final SessionService sessionService;
    @GetMapping("/")
    public ModelAndView showHomePage(Model model) {
        if (sessionService.getCurrentUser() == null) return new ModelAndView("auth/signin");
        System.out.println("User : " + sessionService.getCurrentUser());
        BigDecimal balance = userService.getUserAccount().getBalance();
        if (balance != null) {
            model.addAttribute("balance", userService.getUserAccount().getBalance());

        }
        model.addAttribute("email", sessionService.getCurrentUser().getEmail());
        return new ModelAndView("home");
    }

    @GetMapping("/app")
    public String showHome(Model model){
    return "redirect:/";    }

}
