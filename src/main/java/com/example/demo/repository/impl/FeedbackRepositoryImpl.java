package com.example.demo.repository.impl;

import java.util.Objects;

import com.example.demo.model.Feedback;
import com.example.demo.repository.FeedbackRepository;

public class FeedbackRepositoryImpl extends CrudImpl<Feedback, Long> implements FeedbackRepository {
    private static FeedbackRepositoryImpl instance;

    private FeedbackRepositoryImpl() {
    }

    public static synchronized FeedbackRepositoryImpl getInstance() {
        instance = Objects.requireNonNullElseGet(instance, FeedbackRepositoryImpl::new);
        return instance;
    }
}