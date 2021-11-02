package com.group3.services.category;

import com.group3.models.category.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService implements ICategoryService{
    @Override
    public Iterable<Category> findAll() {
        return null;
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Category> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Category save(Category category) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
