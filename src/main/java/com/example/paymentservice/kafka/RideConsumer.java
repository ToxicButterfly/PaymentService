package com.example.paymentservice.kafka;

import com.example.paymentservice.dto.DelegationFromRidesRequest;
import com.example.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RideConsumer {

    private final PaymentService paymentService;

    @KafkaListener(topics = "${topic.name.ride}")
    public void receiveMessage(DelegationFromRidesRequest request) {
        log.info("Received message: {}", request.toString());
        paymentService.makePayment(request);
    }
}
