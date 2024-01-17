package com.example.paymentservice.service;

import com.example.paymentservice.convert.PaymentTransactionDTOConverter;
import com.example.paymentservice.dao.PaymentDAO;
import com.example.paymentservice.dto.PaymentTransactionDTO;
import com.example.paymentservice.exception.TransactionNotFoundException;
import com.example.paymentservice.model.PaymentTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    PaymentDAO paymentDAO;
    @Autowired
    PaymentTransactionDTOConverter paymentTransactionDTOConverter;

    public ResponseEntity<PaymentTransaction> makePayment(PaymentTransactionDTO paymentDTO) {
        //imitate transaction from passenger's card to driver's card
        PaymentTransaction payment = paymentTransactionDTOConverter.convertPaymentTransactionDTOToPaymentTransaction(paymentDTO);
        paymentDAO.save(payment);
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

    public ResponseEntity<List<PaymentTransaction>> getDriverTransactionsById(Integer id) throws TransactionNotFoundException {
        Optional<List<PaymentTransaction>> transactions = paymentDAO.findAllByDriverId(id);
        if(transactions.isEmpty())
            throw new TransactionNotFoundException("No payment transactions of such driver was found");
        return new ResponseEntity<>(transactions.get(), HttpStatus.OK);
    }

    public ResponseEntity<List<PaymentTransaction>> getPassengerTransactionsById(Integer id) throws TransactionNotFoundException {
        Optional<List<PaymentTransaction>> transactions = paymentDAO.findAllByPassengerId(id);
        if(transactions.isEmpty())
            throw new TransactionNotFoundException("No payment transactions of such passenger was found");
        return new ResponseEntity<>(transactions.get(), HttpStatus.OK);
    }
}
