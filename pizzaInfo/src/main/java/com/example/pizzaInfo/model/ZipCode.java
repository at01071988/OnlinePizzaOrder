package com.example.pizzaInfo.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class ZipCode {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String value;
}
