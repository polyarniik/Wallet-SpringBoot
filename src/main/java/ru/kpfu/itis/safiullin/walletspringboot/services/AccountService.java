package ru.kpfu.itis.safiullin.walletspringboot.services;

import ru.kpfu.itis.safiullin.walletspringboot.dto.AccountDto;
import ru.kpfu.itis.safiullin.walletspringboot.forms.SignUpForm;

public interface AccountService {
    void singUpAccount(SignUpForm form);

    AccountDto findById(Long id);
}
