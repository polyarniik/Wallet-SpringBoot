package ru.kpfu.itis.safiullin.walletspringboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.safiullin.walletspringboot.dto.AccountDto;
import ru.kpfu.itis.safiullin.walletspringboot.dto.BankDto;
import ru.kpfu.itis.safiullin.walletspringboot.exceptions.NoSuchUserException;
import ru.kpfu.itis.safiullin.walletspringboot.forms.SignUpForm;
import ru.kpfu.itis.safiullin.walletspringboot.models.Account;
import ru.kpfu.itis.safiullin.walletspringboot.models.Bank;
import ru.kpfu.itis.safiullin.walletspringboot.repositories.AccountRepository;

import java.util.Collections;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final BankService bankService;

    private final PasswordEncoder passwordEncoder;

    private final BankDto bankDtoForNewUser;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder, BankService bankService) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.bankService = bankService;
        this.bankDtoForNewUser = BankDto.builder()
                .amount(0f)
                .color("#ff5733")
                .name("Наличные")
                .build();
    }

    @Override
    public void singUpAccount(SignUpForm form) {
        if ((accountRepository.findByEmail(form.getEmail())).isPresent()) {
            throw new NoSuchUserException();
        }
        Account account = Account.builder()
                .name(form.getName())
                .email(form.getEmail())
                .password(passwordEncoder.encode(form.getPassword1()))
                .role(Account.Role.USER)
                .state(Account.State.ACTIVE)
                .build();
        account = accountRepository.save(account);
        bankService.addBankToUser(bankDtoForNewUser, account.getId());
    }

    @Override
    public AccountDto findById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            return AccountDto.fromAccount(account.get());
        } else {
            throw new NoSuchUserException();
        }
    }

}
