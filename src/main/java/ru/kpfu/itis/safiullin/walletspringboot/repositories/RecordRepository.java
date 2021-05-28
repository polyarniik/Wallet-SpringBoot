package ru.kpfu.itis.safiullin.walletspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.kpfu.itis.safiullin.walletspringboot.models.Account;
import ru.kpfu.itis.safiullin.walletspringboot.models.Record;

import java.util.List;
import java.util.Optional;

public interface RecordRepository extends JpaRepository<Record, Long> {

    List<Record> findRecordsByAccount_IdOrderByDateDesc(Long id);

    Optional<Record> findRecordById(Long id);

    void deleteById(Long id);

    @Query(nativeQuery = true, value = "WITH _tag_record as (" +
            "SELECT * FROM tag LEFT JOIN tag_record tr on tag.id = tr.tag_id)" +
            "SELECT * FROM _tag_record " +
            "INNER JOIN record r on r.id = _tag_record.record_id " +
            "WHERE _tag_record.name=:tag_name")
    List<Record> findRecordsByTagName(@Param("tag_name") String tag_name);

//    @Query(nativeQuery = true, value = "")
//    List<Record> findRecordsWithMostPopularTag();
}

