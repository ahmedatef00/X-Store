package com.example.demo.repository;

import com.example.demo.model.Product;
import com.example.demo.model.Review;

import java.util.List;

public interface ReviewRepository extends Crud<Review , Long> {
    List<Review> findByProduct(Product product);
}
