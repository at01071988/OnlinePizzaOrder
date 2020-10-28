package com.example.pizzaInfo.service;

import com.example.pizzaInfo.Exceptions.UserNotFound;
import com.example.pizzaInfo.model.Pizza;
import com.example.pizzaInfo.model.PizzaInfoResponse;
import com.example.pizzaInfo.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PizzaService {
    @Autowired
    PizzaRepository pizzaRepository;

    public PizzaInfoResponse create(Pizza pizza) {
        Pizza pizza1 = pizzaRepository.findByName(pizza.getName());
        if (pizza1 == null) {
            pizza1 = pizzaRepository.save(pizza);
            PizzaInfoResponse  pizzaInfoResponse = setPizzaInfo(pizza1);
            return pizzaInfoResponse;
        }
        return null;
    }

    public List<PizzaInfoResponse> getPizzas(){
        List<Pizza> pizzas = pizzaRepository.findAll();
        List<PizzaInfoResponse> pizzaList = new ArrayList<>();
        for(Pizza pizza1 : pizzas){
            PizzaInfoResponse pizzaInfoResponse = setPizzaInfo(pizza1);
            pizzaList.add(pizzaInfoResponse);
        }
        return pizzaList;
    }

    public String delete(int id) throws UserNotFound {
        Optional<Pizza> pizza1 = pizzaRepository.findById(id);
        if(!pizza1.isPresent())
            throw new UserNotFound("User not found");
        else
            pizzaRepository.deleteById(id);
        return "deleted Successfully";
    }

    private PizzaInfoResponse setPizzaInfo(Pizza pizza){
        PizzaInfoResponse  pizzaInfoResponse = new PizzaInfoResponse();
            String[] toppings = pizza.getToppings().split(",\\s"); // remove space and ,
            pizzaInfoResponse.setName(pizza.getName());
            pizzaInfoResponse.setToppings(toppings);
            pizzaInfoResponse.setImage(pizza.getImageLocation());
            pizzaInfoResponse.setPrice(pizza.getPrice());
        return pizzaInfoResponse;
    }
}

