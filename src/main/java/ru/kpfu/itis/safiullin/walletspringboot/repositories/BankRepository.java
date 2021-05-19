package ru.kpfu.itis.safiullin.walletspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.kpfu.itis.safiullin.walletspringboot.models.Bank;

import java.util.List;

public interface BankRepository extends JpaRepository<Bank, Long> {

    List<Bank> findBanksByAccount_Id(Long id);

    @Query(nativeQuery = true,
            value = "UPDATE bank set bank.amount = :amount where bank.id = :id")
    void setAmount(@Param("id") Long id, @Param("amount") Float amount);
}