package ru.kpfu.itis.safiullin.walletspringboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.itis.safiullin.walletspringboot.forms.SignUpForm;
import ru.kpfu.itis.safiullin.walletspringboot.services.AccountService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class SignUpController {

    private final AccountService accountService;

    @Autowired
    public SignUpController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/signUp")
    public String getSingUp(Model model) {
        model.addAttribute("form",
                SignUpForm.builder()
                        .name("")
                        .email("")
                        .password1("")
                        .password2("")
                        .build()
        );
        return "sign_up_page";
    }

    @PostMapping("/signUp")
    public String signUp(@Valid SignUpForm form, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            System.out.print("error ");
            System.out.println(result.getAllErrors().get(0).shouldRenderDefaultMessage());
            model.addAttribute("errorList", result.getAllErrors());
            model.addAttribute("form", form);
            return "sign_up_page";
        }
        accountService.singUpAccount(form);
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
        return "redirect:/wallet";
    }
}
