package com.example.demo.service.impl;

import com.example.demo.model.Product;
import com.example.demo.model.Review;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.repository.impl.ReviewRepositoryImpl;
import com.example.demo.service.ReviewService;

import java.util.List;
import java.util.Objects;

public class ReviewServiceImpl implements ReviewService {
    private static ReviewServiceImpl instance;
    ReviewRepository reviewRepository = ReviewRepositoryImpl.getInstance();

    private ReviewServiceImpl() {
    }

    public static ReviewServiceImpl getInstance() {
        return instance = Objects.requireNonNullElseGet(instance, ReviewServiceImpl::new);
    }

    @Override
    public List<Review> findByProduct(Product product) {
        return reviewRepository.findByProduct(product);
    }

    @Override
    public Review createReview(Review review) {
        return
                reviewRepository.save(review);
    }
}
