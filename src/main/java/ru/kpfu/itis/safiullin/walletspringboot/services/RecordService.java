package ru.kpfu.itis.safiullin.walletspringboot.services;

import ru.kpfu.itis.safiullin.walletspringboot.dto.AccountDto;
import ru.kpfu.itis.safiullin.walletspringboot.dto.RecordDto;
import ru.kpfu.itis.safiullin.walletspringboot.models.Record;

import java.util.List;
import java.util.Optional;

public interface RecordService {
    List<Record> findAccountRecords(AccountDto account);
    void deleteRecord(RecordDto record);
    void editRecord(RecordDto record);
    void addRecord(RecordDto record);
    Optional<Record> getRecordByID(Long recordID);
}
