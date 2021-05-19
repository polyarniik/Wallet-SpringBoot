package ru.kpfu.itis.safiullin.walletspringboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.safiullin.walletspringboot.repositories.AccountRepository;

@Service
public class SingUpService {

    @Autowired
    private AccountRepository accountRepository;


}
