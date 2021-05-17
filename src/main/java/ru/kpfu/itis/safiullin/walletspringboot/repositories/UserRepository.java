package ru.kpfu.itis.safiullin.walletspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.safiullin.walletspringboot.models.Account;

public interface UserRepository extends JpaRepository<Account, Long> {
}
