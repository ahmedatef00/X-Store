package com.example.demo.service.impl;

import com.example.demo.model.Feedback;
import com.example.demo.repository.FeedbackRepository;
import com.example.demo.repository.impl.FeedbackRepositoryImpl;
import com.example.demo.service.FeedbackService;

import java.util.List;
import java.util.Objects;

public class FeedbackServiceImpl implements FeedbackService {

    private static FeedbackServiceImpl instance;

    FeedbackRepository feedbackRepository;

    protected FeedbackServiceImpl() {
        this.feedbackRepository = FeedbackRepositoryImpl.getInstance();
    }

    public static synchronized FeedbackServiceImpl getInstance() {
        instance = Objects.requireNonNullElseGet(instance, FeedbackServiceImpl::new);
        return instance;
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public void createFeedback(Feedback feedback) {
        feedbackRepository.save(feedback);
    }

}
