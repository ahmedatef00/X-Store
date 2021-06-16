package com.example.demo.service;

import com.example.demo.model.Feedback;

import java.util.List;

public interface FeedbackService {
    List<Feedback> getAllFeedbacks();
    void createFeedback(Feedback feedback);
}
