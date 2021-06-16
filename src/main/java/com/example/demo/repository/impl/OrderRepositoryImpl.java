package com.example.demo.repository.impl;

import com.example.demo.model.*;
import com.example.demo.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public class OrderRepositoryImpl extends CrudImpl<Order, Long> implements OrderRepository {
    private static OrderRepositoryImpl instance;

    private OrderRepositoryImpl() {
    }

    public static synchronized OrderRepositoryImpl getInstance() {
        return Objects.requireNonNullElseGet(instance, () -> {
            instance = new OrderRepositoryImpl();
            return instance;
        });
    }

    @Override
    public Order createOrder(User user, Set<Purchase> purchaseSet, Double orderTotal) {
        Order order = new Order();
        order.setOrderTimestamp(LocalDateTime.now());

        getEntityManager().getTransaction().begin();
        user.addOrder(order);
        user.setBalance(user.getBalance() - orderTotal);
        getEntityManager().persist(order);

        purchaseSet.forEach(purchase -> {
            purchase.setOrderProductId(createOrderProductId(order, purchase));
            Product product = purchase.getProduct();
            product.setQuantity(product.getQuantity() - purchase.getQuantity());
            getEntityManager().persist(product);
            getEntityManager().persist(purchase);
            order.getPurchases().add(purchase);
        });
        getEntityManager().getTransaction().commit();
        User byId = UserRepositoryImpl.getInstance().findById(user.getUserId());

        return order;
    }

    private OrderProductId createOrderProductId(Order order, Purchase purchase) {
        OrderProductId orderProductId = new OrderProductId();
        orderProductId.setOrderId(order.getOrderId());
        orderProductId.setProductId(purchase.getProduct().getProductId());
        return orderProductId;
    }
}
