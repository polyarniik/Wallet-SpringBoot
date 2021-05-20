package ru.kpfu.itis.safiullin.walletspringboot.services;

import ru.kpfu.itis.safiullin.walletspringboot.dto.AccountDto;
import ru.kpfu.itis.safiullin.walletspringboot.forms.SignUpForm;

import java.util.Optional;

public interface AccountService {
    Optional<AccountDto> findByEmail(String email);

    void singUpAccount(SignUpForm form);
}
