package ru.kpfu.itis.safiullin.walletspringboot.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.RedirectView;
import ru.kpfu.itis.safiullin.walletspringboot.dto.RecordDto;
import ru.kpfu.itis.safiullin.walletspringboot.forms.RecordForm;
import ru.kpfu.itis.safiullin.walletspringboot.security.details.AccountDetailsImpl;
import ru.kpfu.itis.safiullin.walletspringboot.services.AccountService;
import ru.kpfu.itis.safiullin.walletspringboot.services.BankService;
import ru.kpfu.itis.safiullin.walletspringboot.services.CategoryService;
import ru.kpfu.itis.safiullin.walletspringboot.services.RecordService;

@Controller
@SessionAttributes(types = RecordDto.class)
public class RecordController {

    private final RecordService recordService;

    private final AccountService accountService;

    private final BankService bankService;

    private final CategoryService categoryService;

    public RecordController(RecordService recordService, AccountService accountService, BankService bankService, CategoryService categoryService) {
        this.recordService = recordService;
        this.accountService = accountService;
        this.bankService = bankService;
        this.categoryService = categoryService;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/")
    public RedirectView createRecord(@AuthenticationPrincipal AccountDetailsImpl account, RecordForm recordForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", result.getAllErrors());
            model.addAttribute("recordForm", recordForm);
        }
        recordService.addRecord(recordForm, account.getId());
        return new RedirectView("/");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/del/{record-id}")
    public RedirectView deleteRecord(@PathVariable("record-id") Long recordId) {
        System.out.println("recordId = " + recordId);
        try {
            recordService.deleteRecord(recordId);
        } catch (IllegalArgumentException ex) {
            return new RedirectView("redirect");
        }
        return new RedirectView("/");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/edit/{record-id}")
    public RedirectView editRecord(@PathVariable("record-id") Long recordId, Model model) {
        RecordDto recordDto = recordService.getRecordByID(recordId);
        if (recordDto == null) {
            return new RedirectView("/");
        }
        model.addAttribute(recordDto);
        return new RedirectView("/");

    }
}
