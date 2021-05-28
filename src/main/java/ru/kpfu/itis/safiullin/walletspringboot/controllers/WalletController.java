package ru.kpfu.itis.safiullin.walletspringboot.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.kpfu.itis.safiullin.walletspringboot.dto.AccountDto;
import ru.kpfu.itis.safiullin.walletspringboot.dto.BankDto;
import ru.kpfu.itis.safiullin.walletspringboot.dto.CategoryDto;
import ru.kpfu.itis.safiullin.walletspringboot.dto.RecordDto;
import ru.kpfu.itis.safiullin.walletspringboot.forms.RecordForm;
import ru.kpfu.itis.safiullin.walletspringboot.security.details.AccountDetailsImpl;
import ru.kpfu.itis.safiullin.walletspringboot.services.*;

import java.util.List;

@Controller
@SessionAttributes(types = RecordDto.class)
public class WalletController {

    private final AccountService accountService;

    private final BankService bankService;

    private final RecordService recordService;

    private final CategoryService categoryService;

    private final CurrencyService currencyService;

    public WalletController(AccountService accountService, BankService bankService, RecordService recordService, CategoryService categoryService, CurrencyService currencyService) {
        this.accountService = accountService;
        this.bankService = bankService;
        this.recordService = recordService;
        this.categoryService = categoryService;
        this.currencyService = currencyService;
    }

    @GetMapping("/")
    @PreAuthorize("isAuthenticated()")
    public String getWalletPage(
            Model model,
            @AuthenticationPrincipal AccountDetailsImpl accountDetails,
            RecordDto recordDto) {
        AccountDto account = accountService.findById(accountDetails.getId());
        model.addAttribute("currentUser", account);
        List<BankDto> banks = bankService.getAccountBanks(accountDetails.getId());
        List<RecordDto> records = recordService.findAccountRecords(accountDetails.getId());
        List<CategoryDto> categories = categoryService.findAll();
        model.addAttribute("banks", banks);
        model.addAttribute("records", records);
        model.addAttribute("categories", categories);
        model.addAttribute("recordForm", new RecordForm());
//        System.out.println(records.get(0).getCategories());
//        model.addAttribute("currency", currencyService.getCurrency());
        if (recordDto.getId() != null) {
            model.addAttribute("editRecord", recordDto);
        }
        return "views/main";
    }
}
