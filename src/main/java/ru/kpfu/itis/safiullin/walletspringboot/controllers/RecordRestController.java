package ru.kpfu.itis.safiullin.walletspringboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.safiullin.walletspringboot.dto.RecordDto;
import ru.kpfu.itis.safiullin.walletspringboot.exceptions.NoSuchRecordException;
import ru.kpfu.itis.safiullin.walletspringboot.forms.RecordForm;
import ru.kpfu.itis.safiullin.walletspringboot.services.AccountService;
import ru.kpfu.itis.safiullin.walletspringboot.services.RecordService;

import java.util.List;

@RestController
public class RecordRestController {
    private final RecordService recordService;

    private final AccountService accountService;

    @Autowired
    public RecordRestController(RecordService recordService, AccountService accountService) {
        this.recordService = recordService;
        this.accountService = accountService;
    }

    @GetMapping("/records/{account-id}")
    public ResponseEntity<List<RecordDto>> getRecords(@PathVariable("account-id") Long accountId) {
        List<RecordDto> records = recordService.findAccountRecords(accountId);
        if (records != null) {
            return ResponseEntity.ok(records);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/record/{account-id}/{record-id}")
    public ResponseEntity<RecordDto> getRecord(@PathVariable("account-id") Long accountId,
                                               @PathVariable("record-id") Long recordId) {
        List<RecordDto> records = recordService.findAccountRecords(accountId);
        RecordDto record = records.stream().filter(recordDto -> recordDto.getId().equals(recordId)).findFirst().orElse(null);
        if (record != null) {
            return ResponseEntity.ok(record);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/record/{account-id}")
    public ResponseEntity<RecordDto> createRecord(@PathVariable("account-id") Long accountId,
                                                  @RequestBody RecordForm recordForm,
                                                  BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            RecordDto record = recordService.addRecord(recordForm, accountId);
            return ResponseEntity.ok(record);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("edit-record/{account-id}/{record-id}")
    public ResponseEntity<RecordDto> getRecord(@PathVariable("account-id") Long accountId,
                                               @PathVariable("record-id") Long recordId,
                                               @RequestBody RecordForm recordForm,
                                               BindingResult bindingResult) {
        try {
            if (!bindingResult.hasErrors()) {
                RecordDto recordDto = recordService.editRecord(recordForm, recordId, accountId);
                return ResponseEntity.ok(recordDto);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (NoSuchRecordException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/record/{record-id}")
    public String deleteRecord(@PathVariable("record-id") Long recordId,
                               BindingResult bindingResult,
                               @RequestBody RecordForm recordForm) {
        try {
            recordService.deleteRecord(recordId);
            return "OK";
        } catch (NoSuchRecordException ex) {
            return "FAIL";
        }
    }
}
