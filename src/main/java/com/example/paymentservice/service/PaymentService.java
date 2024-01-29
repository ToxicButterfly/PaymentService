package com.example.paymentservice.service;

import com.example.paymentservice.convert.PaymentTransactionDtoConverter;
import com.example.paymentservice.repo.PaymentRepo;
import com.example.paymentservice.dto.DelegationFromRidesRequest;
import com.example.paymentservice.exception.TransactionNotFoundException;
import com.example.paymentservice.feign.DriverFeignInterface;
import com.example.paymentservice.feign.PassengerFeignInterface;
import com.example.paymentservice.model.BankCard;
import com.example.paymentservice.model.PaymentTransaction;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class PaymentService {

    final PaymentRepo paymentRepo;
    private final PaymentTransactionDtoConverter paymentTransactionDtoConverter;
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

    public ResponseEntity<List<PaymentTransaction>> getDriverTransactionsById(Integer id) throws TransactionNotFoundException {
        Optional<List<PaymentTransaction>> transactions = paymentRepo.findAllByDriverId(id);
        if(transactions.isEmpty())
            throw new TransactionNotFoundException("No payment transactions of such driver was found");
        return new ResponseEntity<>(transactions.get(), HttpStatus.OK);
    }

    public ResponseEntity<List<PaymentTransaction>> getPassengerTransactionsById(Integer id) throws TransactionNotFoundException {
        Optional<List<PaymentTransaction>> transactions = paymentRepo.findAllByPassengerId(id);
        if(transactions.isEmpty())
            throw new TransactionNotFoundException("No payment transactions of such passenger was found");
        return new ResponseEntity<>(transactions.get(), HttpStatus.OK);
    }
}
