package ru.kpfu.itis.safiullin.walletspringboot.services;

import ru.kpfu.itis.safiullin.walletspringboot.models.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<Category> findCategoryById(Long id);

    List<Category> findAll();
}
