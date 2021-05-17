package ru.kpfu.itis.safiullin.walletspringboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.safiullin.walletspringboot.repositories.UserRepository;

@Service
public class SingUpService {

    @Autowired
    private UserRepository userRepository;


}
