package ru.kpfu.itis.safiullin.walletspringboot.services;

import ru.kpfu.itis.safiullin.walletspringboot.dto.RecordDto;
import ru.kpfu.itis.safiullin.walletspringboot.forms.RecordForm;

import java.util.List;

public interface RecordService {
    List<RecordDto> findAccountRecords(Long accountId);

    void deleteRecord(Long recordId);

    RecordDto editRecord(RecordForm record, Long recordId, Long accountId);

    RecordDto addRecord(RecordForm record, Long accountId);

    RecordDto getRecordByID(Long recordID);
}
