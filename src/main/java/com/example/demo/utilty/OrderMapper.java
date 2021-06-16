package com.example.demo.utilty;

import com.example.demo.model.Order;
import com.example.demo.model.Purchase;
import com.example.demo.model.dto.OrderDto;
import com.example.demo.model.dto.PurchaseDto;
import com.example.demo.service.OrderService;
import com.example.demo.service.impl.OrderServiceImpl;

import java.util.HashSet;
import java.util.Set;

public class OrderMapper {
    private static OrderService orderService = OrderServiceImpl.getInstance();
    public static OrderDto mapOrder(Order order) {
        Set<PurchaseDto> purchases = new HashSet<>();
        for (Purchase purchase : order.getPurchases()) {
            purchases.add(PurchaseMapper.mapPurchase(purchase));
        }
        return new OrderDto(order.getOrderId(), order.getOrderTimestamp().toString(), purchases);
    }

    public static Order mapOrder(OrderDto orderDto) {
        return orderService.findById(orderDto.getOrderId());
    }

}
