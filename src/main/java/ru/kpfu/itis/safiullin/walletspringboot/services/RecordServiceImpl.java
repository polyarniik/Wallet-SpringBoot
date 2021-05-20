package ru.kpfu.itis.safiullin.walletspringboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.safiullin.walletspringboot.dto.AccountDto;
import ru.kpfu.itis.safiullin.walletspringboot.dto.RecordDto;
import ru.kpfu.itis.safiullin.walletspringboot.models.Account;
import ru.kpfu.itis.safiullin.walletspringboot.models.Bank;
import ru.kpfu.itis.safiullin.walletspringboot.models.Category;
import ru.kpfu.itis.safiullin.walletspringboot.models.Record;
import ru.kpfu.itis.safiullin.walletspringboot.repositories.RecordRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RecordServiceImpl implements RecordService {
    private final RecordRepository recordRepository;

    @Autowired
    public RecordServiceImpl(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public List<Record> findAccountRecords(AccountDto account) {
        return recordRepository.findRecordsByAccount(Account.builder().id(account.getId()).build());
    }

    @Override
    public void deleteRecord(RecordDto record) {
        recordRepository.delete(Record.builder()
                .id(record.getId())
                .build());
    }

    @Override
    public void editRecord(RecordDto record) {

    }

    @Override
    public void addRecord(RecordDto record) {
        recordRepository.save(Record.builder()
                .account(Account.builder().id(record.getAccount().getId()).build())
                .bank(Bank.builder().id(record.getBank().getId()).build())
                .description(record.getDescription())
                .sum(record.getSum())
                .category(Category.builder().id(record.getCategory().getId()).build())
                .date(new Date())
                .build());
    }

    @Override
    public Optional<Record> getRecordByID(Long recordID) {
        return recordRepository.findRecordById(recordID);
    }
}
