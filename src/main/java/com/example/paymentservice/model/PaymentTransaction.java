package com.example.paymentservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;
    @NotNull(message = "Ride id field must not be empty")
    private Integer rideId;
    @NotNull(message = "Passenger id field must not be empty")
    private Integer passengerId;
    @NotNull(message = "Driver id field must not be empty")
    private Integer driverId;
    @PositiveOrZero(message = "Amount field can't be negative")
    private Float amount;
    private boolean successful;
}
