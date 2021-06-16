package com.example.demo.repository;

import com.example.demo.model.Order;
import com.example.demo.model.Purchase;
import com.example.demo.model.User;

import java.util.Set;

public interface OrderRepository extends Crud<Order, Long> {
    Order createOrder(User user, Set<Purchase> purchaseSet, Double orderTotal);
}
