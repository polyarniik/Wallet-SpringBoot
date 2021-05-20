package ru.kpfu.itis.safiullin.walletspringboot.services;

import ru.kpfu.itis.safiullin.walletspringboot.dto.AccountDto;
import ru.kpfu.itis.safiullin.walletspringboot.dto.BankDto;
import ru.kpfu.itis.safiullin.walletspringboot.models.Bank;

import java.util.List;
import java.util.Optional;

public interface BankService {
    void save(BankDto bank);

    Optional<Bank> findById(Long id);

    void delete(BankDto bank);

    List<Bank> getAccountBanks(AccountDto account);

    void changeAmount(Long bankID, float amount);

    List<Bank> getNotUserBanks(Long id);
}
