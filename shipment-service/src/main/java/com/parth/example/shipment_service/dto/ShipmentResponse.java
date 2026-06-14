package com.parth.example.shipment_service.dto;

import lombok.*;
import com.parth.example.shipment_service.entity.ShipmentStatus;
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
    private ShipmentStatus status;
}