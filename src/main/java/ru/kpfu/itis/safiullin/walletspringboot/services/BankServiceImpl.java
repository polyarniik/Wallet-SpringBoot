package ru.kpfu.itis.safiullin.walletspringboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.safiullin.walletspringboot.dto.AccountDto;
import ru.kpfu.itis.safiullin.walletspringboot.dto.BankDto;
import ru.kpfu.itis.safiullin.walletspringboot.models.Bank;
import ru.kpfu.itis.safiullin.walletspringboot.repositories.BankRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BankServiceImpl implements BankService {
    private final BankRepository bankRepository;

    @Autowired
    public BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public void save(BankDto bank) {
        bankRepository.save(
                Bank.builder()
                        .name(bank.getName())
                        .color(bank.getColor())
                        .amount(bank.getAmount())
                        .build()
        );
    }

    @Override
    public Optional<Bank> findById(Long id) {
        return bankRepository.findById(id);
    }

    @Override
    public void delete(BankDto bank) {
        bankRepository.delete(
                Bank.builder()
                        .id(bank.getId())
                        .build()
        );
    }

    @Override
    public List<Bank> getAccountBanks(AccountDto account) {
        return bankRepository.findBanksByAccount_Id(account.getId());
    }

    @Override
    public void changeAmount(Long bankID, float amount) {
        bankRepository.setAmount(bankID, amount);
    }

    @Override
    public List<Bank> getNotUserBanks(Long id) {
        List<Bank> allBanks = bankRepository.findAll();
        List<Bank> userBanks = bankRepository.findBanksByAccount_Id(id);
        for (Bank bank : userBanks) {
            bank.setAmount(0);
            allBanks.remove(bank);
        }
        return allBanks;
    }
}