package com.example.paymentservice.convert;

import com.example.paymentservice.dto.PaymentTransactionDto;
import com.example.paymentservice.model.PaymentTransaction;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentTransactionDtoConverter {

    @Autowired
    private ModelMapper modelMapper;

    public PaymentTransactionDto convertPaymentTransactionToPaymentTransactionDto(PaymentTransaction payment) {
        return modelMapper.map(payment, PaymentTransactionDto.class);
    }

    public PaymentTransaction convertPaymentTransactionDtoToPaymentTransaction(PaymentTransactionDto paymentDto) {
        return modelMapper.map(paymentDto, PaymentTransaction.class);
    }


}