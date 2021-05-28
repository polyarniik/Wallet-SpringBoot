package ru.kpfu.itis.safiullin.walletspringboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignInController {

    @GetMapping("/signIn")
    public String getSignPage() {
        return "views/sign_in_page";
    }

    @GetMapping(value = "signIn", params = {"error"})
    public String singInError(Model model) {
        model.addAttribute("error", "Неправильный email или пароль!");
        System.out.println(model.getAttribute("error"));
        return "views/sign_in_page";
    }

}
