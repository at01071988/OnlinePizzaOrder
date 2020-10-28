package com.example.paymentProcessingService.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class Payment {
    private String id = UUID.randomUUID().toString();
    private CreditCard creditCard;
    private String orderId;
    private double amount;
    private PaymentStatus paymentStatus;

}
