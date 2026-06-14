package com.parth.example.warehouse_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssignShipmentRequest {

    @NotNull
    private Long shipmentId;

    @NotNull
    private Long warehouseId;
}