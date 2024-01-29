package com.example.paymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankCard {
    private String cardNumber;
    private String expirationDate;
    private String cvv;
    private Float balance;
}
