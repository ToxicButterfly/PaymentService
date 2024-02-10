package com.example.paymentservice.service.impl;

import com.example.paymentservice.dto.DelegationFromRidesRequest;
import com.example.paymentservice.dto.TransactionsDto;
import com.example.paymentservice.exception.TransactionNotFoundException;
import com.example.paymentservice.feign.DriverFeignInterface;
import com.example.paymentservice.feign.PassengerFeignInterface;
import com.example.paymentservice.model.BankCard;
import com.example.paymentservice.model.PaymentTransaction;
import com.example.paymentservice.repo.PaymentRepo;
import com.example.paymentservice.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    final PaymentRepo paymentRepo;
    final PassengerFeignInterface passengerFeignInterface;
    final DriverFeignInterface driverFeignInterface;

    public void makePayment(DelegationFromRidesRequest request) {
        BankCard passengerCard = passengerFeignInterface.getBankData(request.getPassId()).getBody();
        BankCard driverCard = driverFeignInterface.getBankData(request.getDriverId()).getBody();
        remittance(passengerCard, driverCard, request.getCost());
        PaymentTransaction transaction = PaymentTransaction
                .builder()
                .rideId(request.getRideId())
                .driverId(request.getDriverId())
                .passengerId(request.getPassId())
                .amount(request.getCost())
                .successful(true)
                .build();
        paymentRepo.save(transaction);
        log.info("Transaction was successful");
    }

    private void remittance(BankCard passengerCard, BankCard driverCard, Float cost) {
        passengerCard.setBalance(passengerCard.getBalance()-cost);
        driverCard.setBalance(driverCard.getBalance()+cost);
    }

    @SneakyThrows
    public TransactionsDto getDriverTransactionsById(Integer id) {
        List<PaymentTransaction> transactions = paymentRepo.findAllByDriverId(id)
                .orElseThrow(() -> new TransactionNotFoundException("No payment transactions of such driver was found"));
        return new TransactionsDto(transactions);
    }

    @SneakyThrows
    public TransactionsDto getPassengerTransactionsById(Integer id) {
        List<PaymentTransaction> transactions = paymentRepo.findAllByPassengerId(id)
                .orElseThrow(() -> new TransactionNotFoundException("No payment transactions of such passenger was found"));
        return new TransactionsDto(transactions);
    }
}
