package com.example.pizzaInfo.service;

import com.example.pizzaInfo.model.ZipCode;
import com.example.pizzaInfo.repository.ZipCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZipCodeService {
    @Autowired
    ZipCodeRepository zipCodeRepository;

    public List<ZipCode> getZipCodes(){
       return zipCodeRepository.findAll();
    }

    public String create(ZipCode zipCode){
        ZipCode zipCode1 = zipCodeRepository.findByName(zipCode.getValue());
        if(zipCode1 == null) {
            zipCodeRepository.save(zipCode);
            return "Saved Successfully";
        }
       return "Already Present";
    }
}
