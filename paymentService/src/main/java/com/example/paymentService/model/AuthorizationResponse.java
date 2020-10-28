package com.example.paymentService.model;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class AuthorizationResponse {
    private boolean approved;
    private String id;
}
