package com.example.demo.repository.impl;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;

import java.util.Objects;

public class CategoryRepositoryImpl extends CrudImpl<Category, Long> implements CategoryRepository {
    private static CategoryRepositoryImpl instance;

    private CategoryRepositoryImpl() {
    }

    public static synchronized CategoryRepositoryImpl getInstance() {
        instance = Objects.requireNonNullElseGet(instance, CategoryRepositoryImpl::new);
        return instance;
    }
}
