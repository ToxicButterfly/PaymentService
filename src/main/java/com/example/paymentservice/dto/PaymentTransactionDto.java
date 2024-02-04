package com.example.paymentservice.dto;

import com.example.paymentservice.model.BankCard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
