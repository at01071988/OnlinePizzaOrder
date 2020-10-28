package com.example.pizzaInfo.controller;

import com.example.pizzaInfo.model.ZipCode;
import com.example.pizzaInfo.service.ZipCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class ZipCodeController {
    @Autowired
    ZipCodeService zipCodeService;

    @GetMapping("/zipcode")
    public ResponseEntity<List<ZipCode>> getZipCodes(){
     return new ResponseEntity<>(zipCodeService.getZipCodes(), HttpStatus.OK);
    }

    @PostMapping("/zipcode")
    public ResponseEntity<String> createZipCode(@RequestBody ZipCode zipCode){
        String msg = zipCodeService.create(zipCode);
        if(msg.equals("Saved Successfully"))
            return new ResponseEntity<>(msg, HttpStatus.CREATED);
        return new ResponseEntity<>(msg, HttpStatus.NOT_ACCEPTABLE);
    }
}
