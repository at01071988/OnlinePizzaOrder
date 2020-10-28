package com.example.paymentProcessingService.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AuthorizationResponse implements Serializable {
    private boolean approved;
    private String id;
}

