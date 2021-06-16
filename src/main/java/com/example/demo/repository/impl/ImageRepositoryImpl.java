package com.example.demo.repository.impl;

import com.example.demo.model.Image;
import com.example.demo.repository.ImageRepository;

import java.util.Objects;

public class ImageRepositoryImpl extends CrudImpl<Image, Long> implements ImageRepository {
    private static ImageRepositoryImpl instance;

    private ImageRepositoryImpl() {
    }

    public static synchronized ImageRepositoryImpl getInstance() {
        instance = Objects.requireNonNullElseGet(instance, ImageRepositoryImpl::new);
        return instance;
    }
}
