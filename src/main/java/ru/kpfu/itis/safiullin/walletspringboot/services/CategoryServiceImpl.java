package ru.kpfu.itis.safiullin.walletspringboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.safiullin.walletspringboot.dto.CategoryDto;
import ru.kpfu.itis.safiullin.walletspringboot.models.Category;
import ru.kpfu.itis.safiullin.walletspringboot.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto findCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            return CategoryDto.fromCategory(category.get());
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public List<CategoryDto> findAll() {
        return CategoryDto.from(categoryRepository.findAll());
    }
}
