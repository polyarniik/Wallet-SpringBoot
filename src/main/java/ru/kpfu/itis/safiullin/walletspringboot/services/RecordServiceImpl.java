package ru.kpfu.itis.safiullin.walletspringboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.safiullin.walletspringboot.dto.RecordDto;
import ru.kpfu.itis.safiullin.walletspringboot.exceptions.NoSuchRecordException;
import ru.kpfu.itis.safiullin.walletspringboot.exceptions.NoSuchUserException;
import ru.kpfu.itis.safiullin.walletspringboot.forms.RecordForm;
import ru.kpfu.itis.safiullin.walletspringboot.models.Account;
import ru.kpfu.itis.safiullin.walletspringboot.models.Bank;
import ru.kpfu.itis.safiullin.walletspringboot.models.Category;
import ru.kpfu.itis.safiullin.walletspringboot.models.Record;
import ru.kpfu.itis.safiullin.walletspringboot.repositories.AccountRepository;
import ru.kpfu.itis.safiullin.walletspringboot.repositories.BankRepository;
import ru.kpfu.itis.safiullin.walletspringboot.repositories.CategoryRepository;
import ru.kpfu.itis.safiullin.walletspringboot.repositories.RecordRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RecordServiceImpl implements RecordService {

    private final BankService bankService;

    private final RecordRepository recordRepository;

    private final AccountRepository accountRepository;

    private final BankRepository bankRepository;

    private final CategoryRepository categoryRepository;

    @Autowired
    public RecordServiceImpl(RecordRepository recordRepository, CategoryRepository categoryRepository, BankService bankService, BankRepository bankRepository, AccountRepository accountRepository) {
        this.recordRepository = recordRepository;
        this.bankService = bankService;
        this.accountRepository = accountRepository;
        this.bankRepository = bankRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<RecordDto> findAccountRecords(Long accountId) {
        try {
            return RecordDto.from(recordRepository.findRecordsByAccount_IdOrderByDateDesc(accountId));
        } catch (NullPointerException ex) {
            throw new NoSuchRecordException();
        }
    }

    @Override
    public void deleteRecord(Long recordId) {
        try {
            recordRepository.deleteById(recordId);
        } catch (EmptyResultDataAccessException ex) {
            throw new NoSuchRecordException();
        }
    }

    @Override
    public RecordDto editRecord(RecordForm recordForm, Long recordId, Long accountId) {
        Optional<Record> recordOptional = recordRepository.findRecordById(recordId);
        if (!recordOptional.isPresent()) {
            throw new NoSuchRecordException();
        }
        Record record = recordOptional.get();
        if (record.getAccount().getId() != accountId) {
            throw new NoSuchRecordException();
        }
        if (!recordForm.getIsIncome()) {
            record.setSum(-record.getSum());
        }
        return RecordDto.fromRecord(recordRepository.save(Record.builder()
                .id(recordId)
                .account(Account.builder().id(accountId).build())
                .category(Category.builder().id(recordForm.getCategoryId()).build())
                .description(recordForm.getDescription())
                .sum(record.getSum())
                .date(recordForm.getDate())
                .build()));
    }

    @Override
    public RecordDto addRecord(RecordForm record, Long accountId) {
        if (!record.getIsIncome()) {
            record.setSum(-record.getSum());
        }
        Optional<Account> account = accountRepository.findById(accountId);
        Optional<Bank> bank = bankRepository.findById(record.getBankId());
        Optional<Category> category = categoryRepository.findById(record.getCategoryId());
        if (!account.isPresent() || !bank.isPresent() || !category.isPresent()) {
            throw new NoSuchUserException();
        }
        Record rec = Record.builder()
                .account(account.get())
                .bank(bank.get())
                .category(category.get())
                .sum(record.getSum())
                .description(record.getDescription())
                .date(record.getDate())
                .build();
        RecordDto recordDto = RecordDto.fromRecord(recordRepository.save(rec));
        bankService.changeAmount(record.getBankId(), record.getSum());
        return RecordDto.fromRecord(recordRepository.findRecordById(recordDto.getId()).get());
    }

    @Override
    public RecordDto getRecordByID(Long recordID) {
        Optional<Record> record = recordRepository.findRecordById(recordID);
        return record.map(RecordDto::fromRecord).orElseThrow(NoSuchUserException::new);
    }
}
