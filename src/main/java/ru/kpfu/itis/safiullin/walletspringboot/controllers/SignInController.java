package ru.kpfu.itis.safiullin.walletspringboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignInController {

    @GetMapping("/signIn")
    public String getSignPage() {
        return "sign_in_page";
    }

//    @PostMapping("signIn")
//    public String postSignInPage() {
//
//    }

}
