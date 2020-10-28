package com.example.paymentService.service;

import com.example.paymentService.model.AuthorizationRequest;
import com.example.paymentService.model.*;
import com.example.paymentService.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Optional;

import static com.example.paymentService.model.PaymentProducerConfig.PAYMENT_EXCHANGE_NAME;
import static com.example.paymentService.model.PaymentProducerConfig.ROUTING_KEY_NAME;

@Service
public class PaymentService {
    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);
    private static final long DEFAULT_RESPONSE_RECEIVE_TIMEOUT = 6000L;
    private AsyncRabbitTemplate asyncRabbitTemplate;

    private RabbitTemplate rabbitTemplate;
    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository,
                          AsyncRabbitTemplate asyncRabbitTemplate,
                          RabbitTemplate rabbitTemplate) {
        this.paymentRepository = paymentRepository;
        this.asyncRabbitTemplate = asyncRabbitTemplate;
        this.rabbitTemplate = rabbitTemplate;
    }

    public Payment savePayment(Payment payment) {
        return this.paymentRepository.save(payment);
    }

    public ListenableFuture<String> makePayment(Payment payment) {
        AuthorizationRequest request = new AuthorizationRequest(payment.getCreditCard(), payment.getAmount(), payment.getId());
        log.info("getting authorization from payment processing service...");
        ListenableFuture<String> st = asyncRabbitTemplate.convertSendAndReceive(PAYMENT_EXCHANGE_NAME, ROUTING_KEY_NAME, request);
        return st;
    }

    public Payment updateById(String id, String status) {
        Optional<Payment> payment = this.paymentRepository.findById(id);
        Payment p = payment.get();
        Payment p1 = null;
        if (payment.isPresent()) {
            for (PaymentStatus ps : PaymentStatus.values()) {
                if (ps.name().equals(status)) {
                    p.setPaymentStatus(PaymentStatus.valueOf(status));
                    p1 = this.paymentRepository.save(p);
                    break;
                }
            }

        }
        return p1;
    }
}
