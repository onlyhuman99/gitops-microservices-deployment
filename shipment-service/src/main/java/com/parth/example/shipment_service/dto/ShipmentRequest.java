package com.parth.example.shipment_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import com.parth.example.shipment_service.entity.ShipmentStatus;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentRequest {

    @NotBlank
    private String trackingNumber;

    @NotBlank
    private String source;

    @NotBlank
    private String destination;

    @NotBlank
    private ShipmentStatus status;
}