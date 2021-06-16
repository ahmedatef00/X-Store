package com.example.demo.service;

import com.example.demo.exception.ProductQuantityLimitExceeded;
import com.example.demo.exception.UserBalanceViolation;
import com.example.demo.model.Order;
import com.example.demo.model.Purchase;
import com.example.demo.model.dto.UserDto;

import java.util.Set;

public interface OrderService {

    Order createOrder(UserDto user, Set<Purchase> purchases) throws UserBalanceViolation
            , ProductQuantityLimitExceeded;
    Order findById(Long id);
}
