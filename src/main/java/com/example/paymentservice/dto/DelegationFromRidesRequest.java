package com.example.paymentservice.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DelegationFromRidesRequest {
    private Integer rideId;
    private Integer driverId;
    private Integer passId;
    private Float cost;

}
