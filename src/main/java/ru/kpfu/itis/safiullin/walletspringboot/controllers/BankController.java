package ru.kpfu.itis.safiullin.walletspringboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kpfu.itis.safiullin.walletspringboot.dto.RecordDto;
import ru.kpfu.itis.safiullin.walletspringboot.forms.BankForm;
import ru.kpfu.itis.safiullin.walletspringboot.models.Bank;
import ru.kpfu.itis.safiullin.walletspringboot.security.details.AccountDetailsImpl;
import ru.kpfu.itis.safiullin.walletspringboot.services.BankService;

@Controller
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/addBank")
    public String getWalletPage(Model model, @AuthenticationPrincipal AccountDetailsImpl accountDetails) {
        model.addAttribute("form", new BankForm());
        return "views/add_bank";
    }
}
