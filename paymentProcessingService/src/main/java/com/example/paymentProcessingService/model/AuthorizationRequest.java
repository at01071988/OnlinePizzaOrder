package com.example.paymentProcessingService.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorizationRequest {
    private CreditCard creditCard;
    private double amount;
    private String id;

}
