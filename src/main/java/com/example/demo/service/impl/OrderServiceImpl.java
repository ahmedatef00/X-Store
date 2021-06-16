package com.example.demo.service.impl;

import com.example.demo.exception.ProductQuantityLimitExceeded;
import com.example.demo.exception.UserBalanceViolation;
import com.example.demo.model.Order;
import com.example.demo.model.Purchase;
import com.example.demo.model.User;
import com.example.demo.model.dto.UserDto;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.impl.OrderRepositoryImpl;
import com.example.demo.repository.impl.UserRepositoryImpl;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class OrderServiceImpl implements OrderService {

    private static OrderServiceImpl instance;
    private UserService userService;
    private OrderRepository orderRepository;

    protected OrderServiceImpl() {
        userService = UserServiceImpl.getInstance();
        orderRepository = OrderRepositoryImpl.getInstance();
        System.err.println("orderRepository >>>>>>>>>>>>>>>>" + orderRepository);
    }

    public static synchronized OrderServiceImpl getInstance() {
        return Objects.requireNonNullElseGet(instance,
                () -> {
                    instance = new OrderServiceImpl();
                    return instance;
                });

    }

    @Override
    public synchronized Order createOrder(UserDto userDto, Set<Purchase> purchases)
            throws UserBalanceViolation, ProductQuantityLimitExceeded {
        AtomicReference<Double> orderTotalMoney = getTotalOfOrder(purchases);

        if (userDto.getBalance() < orderTotalMoney.get()) {
            throw new UserBalanceViolation();
        }
        if (!checkIfIsStockEnough(purchases)) {
            throw new ProductQuantityLimitExceeded();
        }

        User user = UserRepositoryImpl.getInstance().findById(userDto.getUserId());
        return orderRepository.createOrder(user, purchases, orderTotalMoney.get());
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id);
    }

    private AtomicReference<Double> getTotalOfOrder(Set<Purchase> purchases) {
        AtomicReference<Double> orderTotalMoney = new AtomicReference<>(0d);
        purchases.forEach((purchase ->
                orderTotalMoney.updateAndGet(oldTotal ->
                        oldTotal += (purchase.getProductBuyPrice() * purchase.getQuantity()))));
        return orderTotalMoney;
    }

    private Boolean checkIfIsStockEnough(Set<Purchase> purchases) {
        AtomicBoolean isAvailable = new AtomicBoolean(true);
        purchases.forEach(purchase -> {
            if (purchase.getProduct().getQuantity() < purchase.getQuantity())
                isAvailable.set(false);
        });
        return isAvailable.get();
    }

}
