package ru.kpfu.itis.safiullin.walletspringboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.safiullin.walletspringboot.models.Record;
import ru.kpfu.itis.safiullin.walletspringboot.repositories.RecordRepository;

import java.util.List;

@Service
public class RecordService {
    private final RecordRepository recordRepository;

    @Autowired
    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public List<Record> findAccountRecords(Long id) {
        return recordRepository.findRecordsByAccount_Id(id);
    }
}
