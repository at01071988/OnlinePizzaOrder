package com.example.orderService.controller;

import com.example.orderService.model.OrderInfo;
import com.example.orderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping(value = "/order")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public OrderInfo placeOrder(@RequestBody OrderInfo orderInfo) {
        return this.orderService.saveOrder(orderInfo);
    }


    @GetMapping(value = "/order")
    public List<OrderInfo> getAllOrderInfo() {
        return this.orderService.getAllOrders();
    }
}
