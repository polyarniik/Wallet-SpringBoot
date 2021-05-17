package ru.kpfu.itis.safiullin.walletspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.safiullin.walletspringboot.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
