package ru.kpfu.itis.safiullin.walletspringboot.services;

import ru.kpfu.itis.safiullin.walletspringboot.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto findCategoryById(Long id);

    List<CategoryDto> findAll();
}
