package com.example.paymentService.service;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentResponse {
    private String orderId;
    private String status;
    private String message;
}
