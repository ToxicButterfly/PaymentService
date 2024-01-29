package com.example.paymentservice.dto;

import com.example.paymentservice.model.BankCard;

public class PaymentTransactionDto {
    private Integer transactionId;
    private Integer rideId;
    private Integer passengerId;
    private Integer driverId;
    private Float amount;
    private BankCard passengerCard;
    private BankCard driverCard;
    private boolean successful;
}
