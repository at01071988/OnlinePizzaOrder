package com.example.orderService.repository;

import com.example.orderService.model.DeliveryInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryInfoRepository extends JpaRepository<DeliveryInfo, Integer> {
}
