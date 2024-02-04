package com.example.paymentservice.util;

import com.example.paymentservice.dto.DelegationFromRidesRequest;
import com.example.paymentservice.model.BankCard;
import com.example.paymentservice.model.PaymentTransaction;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestUtils {

    public final Integer DEFAULT_ID = 1;
    public final Float DEFAULT_COST = 5F;
    public final String DEFAULT_CARD_NUMBER = "12344567891234567";
    public final String DEFAULT_EXPIRATION_DATE = "01-01-2024";
    public final String DEFAULT_CVV = "123";
    public final Float DEFAULT_BALANCE = 100F;
    public final boolean DEFAULT_SUCCESS_STATE = true;

    public DelegationFromRidesRequest getDefaultDelegationFromRidesRequest() {
        return DelegationFromRidesRequest.builder()
                .rideId(DEFAULT_ID)
                .driverId(DEFAULT_ID)
                .passId(DEFAULT_ID)
                .cost(DEFAULT_COST)
                .build();
    }

    public BankCard getDefaultBankCard() {
        return BankCard.builder()
                .cardNumber(DEFAULT_CARD_NUMBER)
                .expirationDate(DEFAULT_EXPIRATION_DATE)
                .cvv(DEFAULT_CVV)
                .balance(DEFAULT_BALANCE)
                .build();
    }

    public PaymentTransaction getDefaultPaymentTransaction() {
        return PaymentTransaction.builder()
                .transactionId(DEFAULT_ID)
                .rideId(DEFAULT_ID)
                .passengerId(DEFAULT_ID)
                .driverId(DEFAULT_ID)
                .amount(DEFAULT_BALANCE)
                .successful(DEFAULT_SUCCESS_STATE)
                .build();
    }
}
