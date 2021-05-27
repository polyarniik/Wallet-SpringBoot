package ru.kpfu.itis.safiullin.walletspringboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.safiullin.walletspringboot.dto.AccountDto;
import ru.kpfu.itis.safiullin.walletspringboot.dto.RecordDto;
import ru.kpfu.itis.safiullin.walletspringboot.forms.RecordForm;
import ru.kpfu.itis.safiullin.walletspringboot.security.details.AccountDetailsImpl;
import ru.kpfu.itis.safiullin.walletspringboot.services.AccountService;
import ru.kpfu.itis.safiullin.walletspringboot.services.BankService;
import ru.kpfu.itis.safiullin.walletspringboot.services.CategoryService;
import ru.kpfu.itis.safiullin.walletspringboot.services.RecordService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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
    public String createRecord(@AuthenticationPrincipal AccountDetailsImpl account, RecordForm recordForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", result.getAllErrors());
            model.addAttribute("recordForm", recordForm);
        }
        recordService.addRecord(recordForm, account.getId());
        return "redirect:/";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/del/{record-id}")
    public String deleteRecord(@PathVariable("record-id") Long recordId) {
        System.out.println("recordId = " + recordId);
        try {
            recordService.deleteRecord(recordId);
        } catch (IllegalArgumentException ex) {
            return "redirect:/";
        }
        return "redirect:/";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/edit/{record-id}")
    public String editRecord(@PathVariable("record-id") Long recordId, HttpServletRequest request) {
        RecordDto recordDto = recordService.getRecordByID(recordId);
        if (recordDto == null) {
            return "redirect:/";
        }
        request.setAttribute("editRecord", recordDto);
        return "redirect:/";
    }
}
