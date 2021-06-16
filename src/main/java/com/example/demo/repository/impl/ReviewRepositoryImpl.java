package com.example.demo.repository.impl;

import com.example.demo.model.Product;
import com.example.demo.model.Review;
import com.example.demo.repository.ReviewRepository;

import java.util.List;
import java.util.Objects;

public class ReviewRepositoryImpl extends CrudImpl<Review , Long> implements ReviewRepository {
    private static ReviewRepositoryImpl instance = null;

    public static ReviewRepositoryImpl getInstance() {
        return instance = Objects.requireNonNullElseGet(instance ,  ReviewRepositoryImpl::new);
    }

    private ReviewRepositoryImpl() {
    }


    @Override
    public List<Review> findByProduct(Product product) {
         return (List<Review>) getEntityManager().createNamedQuery("Review.findByProduct")
                .setParameter("product", product)
                .getResultList();
    }
}
