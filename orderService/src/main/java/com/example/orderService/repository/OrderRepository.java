package com.example.orderService.repository;


import com.example.orderService.model.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderInfo, Integer> {
}
