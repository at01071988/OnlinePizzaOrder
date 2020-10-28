package com.example.pizzaInfo.repository;

import com.example.pizzaInfo.model.ZipCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ZipCodeRepository extends JpaRepository<ZipCode, Integer> {

    @Query(value = "select * from zip_code where value = ?1", nativeQuery = true)
    public ZipCode findByName(String name);
}
