package com.example.paymentService.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponse {
    private String paymentId;
    private String orderId;
    private boolean isApproved;
    private boolean isTimeout;
    private String message;
    private int deliveryTime;
}
