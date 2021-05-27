package ru.kpfu.itis.safiullin.walletspringboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.itis.safiullin.walletspringboot.forms.SignUpForm;
import ru.kpfu.itis.safiullin.walletspringboot.services.AccountServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class SignUpController {

    private final AccountServiceImpl accountService;

    @Autowired
    public SignUpController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/signUp")
    public String getSingUp(Model model) {
        model.addAttribute("form",new SignUpForm());
        return "views/sign_up_page";
    }

    @PostMapping("/signUp")
    public String signUp(Model model, @Valid SignUpForm form, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("errorList", result.getAllErrors());
            model.addAttribute("form", form);
            return "views/sign_up_page";
        }
        try {
            accountService.singUpAccount(form);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "Аккаунт с таким email уже существует!");
            return "views/sign_up_page";
        }
//        try {
//            userService.addUser(form);
//        }
//        catch (OccupiedEmailException e){
//            model.addAttribute("emailError", true);
//            model.addAttribute("form", form);
//            return "signUp";
//        }
//        try {
//            request.login(form.getEmail(), form.getPassword());
//        } catch (ServletException e) {
//            throw new IllegalArgumentException(e);
//        }
        return "redirect:/";
    }
}
