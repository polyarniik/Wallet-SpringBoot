package ru.kpfu.itis.safiullin.walletspringboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public BankDto findById(Long id) {
        Optional<Bank> bank = bankRepository.findById(id);
        if (bank.isPresent()) {
            return BankDto.fromBank(bank.get());
        } else {
            throw new IllegalArgumentException();
        }
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
    public List<BankDto> getAccountBanks(Long accountId) {
        return BankDto.from(bankRepository.findBanksByAccount_Id(accountId));
    }

    @Override
    public void changeAmount(Long bankID, float amount) {
        Optional<Bank> optionalBank = bankRepository.findById(bankID);
        if (optionalBank.isPresent()) {
            Bank bank = optionalBank.get();
            bank.setAmount(bank.getAmount() + amount);
            bankRepository.save(bank);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
