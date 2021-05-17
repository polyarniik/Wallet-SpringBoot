package ru.kpfu.itis.safiullin.walletspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kpfu.itis.safiullin.walletspringboot.models.Bank;

import java.util.List;

public interface BankRepository extends JpaRepository<Bank, Long> {

    List<Bank> findBanksByAccount_Id(Long id);

    @Query(nativeQuery = true,
            value = "UPDATE bank set bank.amount = :status where bank.id = :id")
    int setAmount(Long id, Float amount);
}