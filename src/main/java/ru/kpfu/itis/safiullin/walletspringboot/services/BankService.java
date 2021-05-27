package ru.kpfu.itis.safiullin.walletspringboot.services;

import ru.kpfu.itis.safiullin.walletspringboot.dto.BankDto;

import java.util.List;

public interface BankService {
    void save(BankDto bank);

    BankDto findById(Long id);

    void delete(BankDto bank);

    List<BankDto> getAccountBanks(Long accountId);

    void changeAmount(Long bankID, float amount);
}
