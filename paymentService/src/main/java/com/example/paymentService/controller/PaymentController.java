package com.example.paymentService.controller;

import com.example.paymentService.model.AuthorizationResponse;
import com.example.paymentService.model.Payment;
import com.example.paymentService.model.PaymentStatus;
import com.example.paymentService.service.PaymentResponse;
import com.example.paymentService.service.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.ExecutionException;

@CrossOrigin
@RestController
public class PaymentController {
    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    private static final String REQUEST_TIMEOUT_RESPONSE = "Sorry, server is busy. Please try again later.";

    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping(value = "/payment")
    public DeferredResult<ResponseEntity<?>> makePayment(@RequestBody Payment payment) throws ExecutionException, InterruptedException {
        DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();
        Payment newPayment = this.paymentService.savePayment(payment);
        ListenableFuture<String> authorizationResponse = this.paymentService.makePayment(newPayment);
        authorizationResponse.addCallback(new ListenableFutureCallback<String>() {
            @SneakyThrows
            @Override
            public void onSuccess(String s) {
                AuthorizationResponse authorizationResponse1 = new ObjectMapper().readValue(s, AuthorizationResponse.class);
                String status = null;
                PaymentResponse paymentResponse = new PaymentResponse();
                if(authorizationResponse1.isApproved()) {
                    status = PaymentStatus.APPROVED.toString();
                    paymentResponse.setMessage("Order has been placed successfully. Thanks you.");
                }else {
                    status = PaymentStatus.DECLINED.toString();
                    paymentResponse.setMessage("Payment is declined. Please re-check your payment method.");
                }
                Payment payment1 = paymentService.updateById(authorizationResponse1.getId(), status);
                paymentResponse.setOrderId(payment1.getId());
                paymentResponse.setStatus(payment1.getPaymentStatus().toString());
                ResponseEntity<PaymentResponse> responseEntity = new ResponseEntity<>(paymentResponse, HttpStatus.OK);
                deferredResult.setResult(responseEntity);
            }
            @Override
            public void onFailure(Throwable throwable) {
                Payment payment1 = paymentService.updateById(newPayment.getId(), PaymentStatus.TIMEOUT.toString());
                PaymentResponse paymentResponse = new PaymentResponse();
                paymentResponse.setOrderId(payment1.getId());
                paymentResponse.setStatus(payment1.getPaymentStatus().toString());
                paymentResponse.setMessage("Payment is declined. Please re-check your payment method.");
                ResponseEntity<PaymentResponse> responseEntity = new ResponseEntity<>(paymentResponse, HttpStatus.REQUEST_TIMEOUT);
                deferredResult.setResult(responseEntity);
            }
        });
        return deferredResult;
    }

    @GetMapping("/paymentStatus")
    public ResponseEntity paymentStatus(@RequestParam("id") String id, @RequestParam("status") String status) {
        Payment payment = this.paymentService.updateById(id, status);
        return new ResponseEntity(payment, HttpStatus.OK);
    }


}
