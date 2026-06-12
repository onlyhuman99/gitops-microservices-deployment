package com.parth.example.shipment_service.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentResponse {

    private Long id;
    private String trackingNumber;
    private String source;
    private String destination;
    private String status;
}