package ru.kpfu.itis.safiullin.walletspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.safiullin.walletspringboot.models.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);
}
