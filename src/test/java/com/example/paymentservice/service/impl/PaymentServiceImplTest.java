package com.example.paymentservice.service.impl;

import com.example.paymentservice.dto.DelegationFromRidesRequest;
import com.example.paymentservice.dto.TransactionsDto;
import com.example.paymentservice.exception.TransactionNotFoundException;
import com.example.paymentservice.feign.DriverFeignInterface;
import com.example.paymentservice.feign.PassengerFeignInterface;
import com.example.paymentservice.model.PaymentTransaction;
import com.example.paymentservice.repo.PaymentRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.example.paymentservice.util.TestUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {
    @Mock
    private PaymentRepo paymentRepo;
    @Mock
    private PassengerFeignInterface passengerFeignInterface;
    @Mock
    private DriverFeignInterface driverFeignInterface;
    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Test
    void makePayment() {
        DelegationFromRidesRequest request = getDefaultDelegationFromRidesRequest();
        doReturn(ResponseEntity.ok(getDefaultBankCard())).when(passengerFeignInterface).getBankData(DEFAULT_ID);
        doReturn(ResponseEntity.ok(getDefaultBankCard())).when(driverFeignInterface).getBankData(DEFAULT_ID);
        doReturn(getDefaultPaymentTransaction()).when(paymentRepo).save(any(PaymentTransaction.class));

        paymentService.makePayment(request);

        verify(passengerFeignInterface).getBankData(DEFAULT_ID);
        verify(driverFeignInterface).getBankData(DEFAULT_ID);
        verify(paymentRepo).save(any(PaymentTransaction.class));
    }

    @Test
    void getDriverTransactionsByIdWhenDriverExist() {
        List transactions = new ArrayList<>(Arrays.asList(getDefaultPaymentTransaction(), getDefaultPaymentTransaction()));
        doReturn(Optional.of(transactions)).when(paymentRepo).findAllByDriverId(DEFAULT_ID);

        TransactionsDto response = paymentService.getDriverTransactionsById(DEFAULT_ID);

        assertEquals(new TransactionsDto(transactions), response);
        verify(paymentRepo).findAllByDriverId(DEFAULT_ID);
    }

    @Test
    void getDriverTransactionsByIdWhenDriverDontExist() {
        doReturn(Optional.empty()).when(paymentRepo).findAllByDriverId(DEFAULT_ID);
        assertThrows(TransactionNotFoundException.class, () -> paymentService.getDriverTransactionsById(DEFAULT_ID));
        verify(paymentRepo).findAllByDriverId(DEFAULT_ID);
    }

    @Test
    void getPassengerTransactionsByIdWhenPassengerExist() {
        List transactions = new ArrayList<>(Arrays.asList(getDefaultPaymentTransaction(), getDefaultPaymentTransaction()));
        doReturn(Optional.of(transactions)).when(paymentRepo).findAllByPassengerId(DEFAULT_ID);

        TransactionsDto response = paymentService.getPassengerTransactionsById(DEFAULT_ID);

        assertEquals(new TransactionsDto(transactions), response);
        verify(paymentRepo).findAllByPassengerId(DEFAULT_ID);
    }

    @Test
    void getPassengerTransactionsByIdWhenPassengerDontExist() {
        doReturn(Optional.empty()).when(paymentRepo).findAllByPassengerId(DEFAULT_ID);
        assertThrows(TransactionNotFoundException.class, () -> paymentService.getPassengerTransactionsById(DEFAULT_ID));
        verify(paymentRepo).findAllByPassengerId(DEFAULT_ID);
    }
}
