package com.example.paymentservice.dto;

import com.example.paymentservice.model.PaymentTransaction;

import java.util.List;

public record TransactionsDto(List<PaymentTransaction> transactionList) {
}
