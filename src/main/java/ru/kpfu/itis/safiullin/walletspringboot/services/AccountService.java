package ru.kpfu.itis.safiullin.walletspringboot.services;

import ru.kpfu.itis.safiullin.walletspringboot.dto.AccountDto;
import ru.kpfu.itis.safiullin.walletspringboot.forms.SignUpForm;

import java.util.Optional;

public interface AccountService {
    void singUpAccount(SignUpForm form);

    AccountDto findById(Long id);
}
