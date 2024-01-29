package com.example.paymentservice.controller;

import com.example.paymentservice.dto.PaymentTransactionDTO;
import com.example.paymentservice.exception.TransactionNotFoundException;
import com.example.paymentservice.model.PaymentTransaction;
import com.example.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/payment")
public class PaymentController {

    final PaymentService paymentService;

    @GetMapping("driver/{id}")
    public ResponseEntity<List<PaymentTransaction>> getDriverTransactionsById(@PathVariable Integer id) throws TransactionNotFoundException {
        return paymentService.getDriverTransactionsById(id);
    }

    @GetMapping("passenger/{id}")
    public ResponseEntity<List<PaymentTransaction>> getPassengerTransactionsById(@PathVariable Integer id) throws TransactionNotFoundException {
        return paymentService.getPassengerTransactionsById(id);
    }
}
