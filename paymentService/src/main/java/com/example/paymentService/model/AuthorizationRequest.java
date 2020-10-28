package com.example.paymentService.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthorizationRequest {
    private CreditCard creditCard;
    private double amount;
    private String id;

    public AuthorizationRequest() {
    }

    public AuthorizationRequest(CreditCard creditCard, Double amount, String id){
        this.creditCard = creditCard;
        this.amount = amount;
        this.id = id;
    }
}
