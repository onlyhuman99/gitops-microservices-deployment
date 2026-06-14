package com.parth.example.warehouse_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseRequest {

    @NotBlank(message = "Warehouse code is required")
    private String warehouseCode;

    @NotBlank(message = "Warehouse name is required")
    private String name;

    @NotBlank(message = "Location is required")
    private String location;

    @Min(value = 1, message = "Capacity must be greater than 0")
    private Integer capacity;

    @Min(value = 0, message = "Available capacity cannot be negative")
    private Integer availableCapacity;
}