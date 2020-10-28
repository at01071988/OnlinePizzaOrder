package com.example.orderService.service;

import com.example.orderService.model.OrderInfo;
import com.example.orderService.repository.DeliveryInfoRepository;
import com.example.orderService.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DeliveryInfoRepository deliveryInfoRepository;

    public OrderInfo saveOrder(OrderInfo orderInfo) {
        return this.orderRepository.save(orderInfo);
    }

    public List<OrderInfo> getAllOrders() {
        return this.orderRepository.findAll();
    }
}
