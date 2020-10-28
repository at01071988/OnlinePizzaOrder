package com.example.paymentProcessingService.service;

import com.example.paymentProcessingService.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentProcessingService {
    private static final Logger log = LoggerFactory.getLogger(PaymentProcessingService.class);

    @Autowired
    RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = PaymentConsumerConfig.REQUEST_QUEUE_NAME)
    public String processPayment(AuthorizationRequest request) throws InterruptedException, JsonProcessingException {
        AuthorizationResponse response = new AuthorizationResponse();
        boolean isApproved = validateCreditCard(request.getCreditCard());
        response.setId(request.getId());
        response.setApproved(isApproved);
        String s = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false).writeValueAsString(response);
        System.out.println("s--"+s);
        return s;
    }


    /*
     * This method is designed to simulate the process of getting authorization from card issuer.
     * In real world, this process may take 2~3 seconds.
     * The case of response timeout is considered also.
     * */
    private static boolean validateCreditCard(CreditCard creditCard) throws InterruptedException {
        log.info("Validating credit card information on thread " + Thread.currentThread().getName());
        long validatingTime = getRandomPaymentProcessTime(1000, 2000);
        Thread.sleep(validatingTime);
        return getRandomApproval();
    }

    private static int getRandomPaymentProcessTime(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }


    private static boolean getRandomApproval() {
        Random rand = new Random();
        if(rand.nextInt(100) < 95) {
            return true;
        }
        return false;
    }
}
