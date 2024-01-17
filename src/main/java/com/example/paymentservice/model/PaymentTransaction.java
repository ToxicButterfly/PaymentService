package com.example.paymentservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Entity
public class PaymentTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;
    @NotEmpty(message = "Ride id field must not be empty")
    private Integer rideId;
    @NotEmpty(message = "Passenger id field must not be empty")
    private Integer passengerId;
    @NotEmpty(message = "Driver id field must not be empty")
    private Integer driverId;
    @PositiveOrZero(message = "Amount field can't be negative")
    private Float amount;
    private boolean successful;
}
