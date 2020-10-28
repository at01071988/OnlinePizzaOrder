package com.example.pizzaInfo.repository;

import com.example.pizzaInfo.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

    @Query(value = "select * from pizza where name = ?1", nativeQuery = true)
    public Pizza findByName(String name);
}
