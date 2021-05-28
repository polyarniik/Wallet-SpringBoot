package ru.kpfu.itis.safiullin.walletspringboot.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.kpfu.itis.safiullin.walletspringboot.dto.BankDto;
import ru.kpfu.itis.safiullin.walletspringboot.forms.BankForm;
import ru.kpfu.itis.safiullin.walletspringboot.security.details.AccountDetailsImpl;
import ru.kpfu.itis.safiullin.walletspringboot.services.BankService;

@Controller
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/addBank")
    public String getBankPage(Model model, @AuthenticationPrincipal AccountDetailsImpl accountDetails) {
        model.addAttribute("form", new BankForm());
        return "views/add_bank";
    }

    @PostMapping("/addBank")
    public RedirectView postBank(@AuthenticationPrincipal AccountDetailsImpl accountDetails,
                                 BankForm bankForm, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            String color = "#F0F0F0";
            bankService.addBankToUser(BankDto.builder()
                            .name(bankForm.getName())
                            .color(color)
                            .amount(bankForm.getAmount())
                            .build(),
                    accountDetails.getId());
            return new RedirectView("/");
        } else {
            return new RedirectView("/addBank");
        }
    }
}
