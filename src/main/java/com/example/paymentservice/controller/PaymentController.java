package com.example.paymentservice.controller;

import com.example.paymentservice.dto.PaymentTransactionDTO;
import com.example.paymentservice.model.PaymentTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("")
    public ResponseEntity<PaymentTransaction> makePayment(@RequestBody PaymentTransactionDTO payment) {
        return paymentService.makePayment(payment);
    }

    @GetMapping("driver/{id}")
    public ResponseEntity<List<PaymentTransaction>> getDriverTransactionsById(@PathVariable Integer id) {
        return paymentService.getDriverTransactionsById(id);
    }

    @GetMapping("passenger/{id}")
    public ResponseEntity<List<PaymentTransaction>> getPassengerTransactionsById(@PathVariable Integer id) {
        return paymentService.getPassengerTransactionsById(id);
    }
}
