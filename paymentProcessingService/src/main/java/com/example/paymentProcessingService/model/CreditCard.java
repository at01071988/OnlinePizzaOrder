package com.example.paymentProcessingService.model;

import lombok.Data;

@Data
public class CreditCard {
    private String creditCardId;
    private String cardNumber;
    private String cardHolderName;
    private String expiryMonth;
    private String expiryYear;
    private String securityCode;


}
