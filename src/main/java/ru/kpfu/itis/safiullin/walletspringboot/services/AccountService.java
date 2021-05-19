package ru.kpfu.itis.safiullin.walletspringboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.safiullin.walletspringboot.forms.SignUpForm;
import ru.kpfu.itis.safiullin.walletspringboot.models.Account;
import ru.kpfu.itis.safiullin.walletspringboot.repositories.AccountRepository;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void singUpAccount(SignUpForm form) {
        if ((accountRepository.findByEmail(form.getEmail())).isPresent()) {
            throw new IllegalStateException();
        }
        Account account = Account.builder()
                .name(form.getName())
                .email(form.getEmail())
                .password(passwordEncoder.encode(form.getPassword1()))
                .role(Account.Role.USER)
                .state(Account.State.ACTIVE)
                .build();
        accountRepository.save(account);
    }

}
