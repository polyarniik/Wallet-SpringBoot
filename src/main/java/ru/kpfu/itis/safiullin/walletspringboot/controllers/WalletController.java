package ru.kpfu.itis.safiullin.walletspringboot.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kpfu.itis.safiullin.walletspringboot.dto.AccountDto;
import ru.kpfu.itis.safiullin.walletspringboot.models.Bank;
import ru.kpfu.itis.safiullin.walletspringboot.models.Record;
import ru.kpfu.itis.safiullin.walletspringboot.security.details.AccountDetailsImpl;
import ru.kpfu.itis.safiullin.walletspringboot.services.AccountServiceImpl;
import ru.kpfu.itis.safiullin.walletspringboot.services.BankServiceImpl;
import ru.kpfu.itis.safiullin.walletspringboot.services.RecordServiceImpl;

import java.util.List;

@Controller
public class WalletController {

    private final AccountServiceImpl accountService;

    private final BankServiceImpl bankService;

    private final RecordServiceImpl recordService;

    public WalletController(AccountServiceImpl accountService, BankServiceImpl bankService, RecordServiceImpl recordService) {
        this.accountService = accountService;
        this.bankService = bankService;
        this.recordService = recordService;
    }


    @GetMapping("/wallet")
    public String getWalletPage(Model model, @AuthenticationPrincipal AccountDetailsImpl accountDetails) {
        model.addAttribute("currentUser", accountService.findByEmail(accountDetails.getUsername()).get());
        AccountDto accountDto = (AccountDto) model.getAttribute("currentUser");
        List<Bank> banks = bankService.getAccountBanks(accountDto);
        List<Record> records = recordService.findAccountRecords(accountDto);
        model.addAttribute("banks", banks);
        model.addAttribute("records", records);
        return "main";
    }
}
