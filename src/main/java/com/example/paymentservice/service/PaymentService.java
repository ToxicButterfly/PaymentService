package com.example.paymentservice.service;

import com.example.paymentservice.dto.DelegationFromRidesRequest;
import com.example.paymentservice.dto.TransactionsDto;
import com.example.paymentservice.exception.TransactionNotFoundException;

public interface PaymentService {
    void makePayment(DelegationFromRidesRequest request);
    TransactionsDto getDriverTransactionsById(Integer id) throws TransactionNotFoundException;
    TransactionsDto getPassengerTransactionsById(Integer id) throws TransactionNotFoundException;

}
