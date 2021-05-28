package ru.kpfu.itis.safiullin.walletspringboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.safiullin.walletspringboot.dto.BankDto;
import ru.kpfu.itis.safiullin.walletspringboot.exceptions.NoSuchBankException;
import ru.kpfu.itis.safiullin.walletspringboot.exceptions.NoSuchUserException;
import ru.kpfu.itis.safiullin.walletspringboot.models.Account;
import ru.kpfu.itis.safiullin.walletspringboot.models.Bank;
import ru.kpfu.itis.safiullin.walletspringboot.repositories.AccountRepository;
import ru.kpfu.itis.safiullin.walletspringboot.repositories.BankRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BankServiceImpl implements BankService {
    private final BankRepository bankRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public BankServiceImpl(BankRepository bankRepository, AccountRepository accountRepository) {
        this.bankRepository = bankRepository;
        this.accountRepository = accountRepository;
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
    public void addBankToUser(BankDto bankDto, Long accountId) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (accountOptional.isPresent()) {
            bankRepository.save(Bank.builder()
                    .account(accountOptional.get())
                    .color(bankDto.getColor())
                    .name(bankDto.getName())
                    .amount(bankDto.getAmount())
                    .build());
        } else {
            throw new NoSuchUserException();
        }
    }

    @Override
    public BankDto findById(Long id) {
        Optional<Bank> bank = bankRepository.findById(id);
        if (bank.isPresent()) {
            return BankDto.fromBank(bank.get());
        } else {
            throw new NoSuchBankException();
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
            throw new NoSuchBankException();
        }
    }
}
