package ru.kpfu.itis.safiullin.walletspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.safiullin.walletspringboot.models.Record;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {
    List<Record> findRecordsByAccount_Id(Long id);
}

