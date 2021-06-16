package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> findByProduct(Product product);
    Review createReview(Review review);
}
