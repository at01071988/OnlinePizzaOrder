package com.example.pizzaInfo.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PizzaInfoResponse {
    private String name;
    private String[] toppings;
    private String image;
    private int price;
}
