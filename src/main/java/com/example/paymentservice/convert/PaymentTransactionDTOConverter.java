package com.example.paymentservice.convert;

import com.example.paymentservice.dto.PaymentTransactionDTO;
import com.example.paymentservice.model.PaymentTransaction;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentTransactionDTOConverter {

    @Autowired
    private ModelMapper modelMapper;

    public PaymentTransactionDTO convertPaymentTransactionToPaymentTransactionDTO(PaymentTransaction payment) {
        return modelMapper.map(payment, PaymentTransactionDTO.class);
    }

    public PaymentTransaction convertPaymentTransactionDTOToPaymentTransaction(PaymentTransactionDTO paymentDTO) {
        return modelMapper.map(paymentDTO, PaymentTransaction.class);
    }


}