package com.example.demo.service.impl;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.impl.CategoryRepositoryImpl;
import com.example.demo.service.CategoryService;

import java.util.List;
import java.util.Objects;

public class  CategoryServiceImpl implements CategoryService {
    private static CategoryServiceImpl instance;
    CategoryRepository categoryRepository;

    protected CategoryServiceImpl() {
        this.categoryRepository = CategoryRepositoryImpl.getInstance();
    }

    public static synchronized CategoryServiceImpl getInstance() {
        instance = Objects.requireNonNullElseGet(instance, CategoryServiceImpl::new);
        return instance;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }
}
