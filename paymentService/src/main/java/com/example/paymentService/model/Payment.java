package com.example.paymentService.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Setter
@Getter
@Entity
public class Payment {
    @Id
    private String id = UUID.randomUUID().toString();
    @ManyToOne(targetEntity = CreditCard.class, cascade = CascadeType.ALL)
    private CreditCard creditCard;
    private String orderId;
    private double amount;
    private PaymentStatus paymentStatus;

}
