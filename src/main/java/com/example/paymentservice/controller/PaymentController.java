package com.example.paymentservice.controller;

import com.example.paymentservice.dto.TransactionsDto;
import com.example.paymentservice.exception.TransactionNotFoundException;
import com.example.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/payment")
public class PaymentController {

    final PaymentService paymentService;

    @GetMapping("driver/{id}")
    public ResponseEntity<TransactionsDto> getDriverTransactionsById(@PathVariable Integer id) throws TransactionNotFoundException {
        return ResponseEntity.ok(paymentService.getDriverTransactionsById(id));
    }

    @GetMapping("passenger/{id}")
    public ResponseEntity<TransactionsDto> getPassengerTransactionsById(@PathVariable Integer id) throws TransactionNotFoundException {
        return ResponseEntity.ok(paymentService.getPassengerTransactionsById(id));
    }
}
