package com.example.pizzaInfo.controller;

import com.example.pizzaInfo.Exceptions.UserNotFound;
import com.example.pizzaInfo.model.Pizza;
import com.example.pizzaInfo.model.PizzaInfoResponse;
import com.example.pizzaInfo.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
public class PizzaController {
    @Autowired
    PizzaService pizzaService;

    @PostMapping("/pizza")
    public ResponseEntity<?> create(@RequestBody Pizza pizza) throws IOException {
        PizzaInfoResponse pizza1 = pizzaService.create(pizza);
        return new ResponseEntity<>(pizza1, HttpStatus.OK);
    }

    @DeleteMapping("/pizza/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) throws UserNotFound {
        String pizza1 = pizzaService.delete(id);
        return new ResponseEntity<>(pizza1, HttpStatus.OK);
    }

    @GetMapping("/pizza")
    public List<PizzaInfoResponse> pizza(){
        return pizzaService.getPizzas();
    }
}
