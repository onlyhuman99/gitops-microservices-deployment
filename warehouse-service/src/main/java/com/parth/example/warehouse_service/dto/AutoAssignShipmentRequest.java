package com.parth.example.warehouse_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AutoAssignShipmentRequest {

    @NotNull
    private Long shipmentId;
}