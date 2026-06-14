package com.parth.example.warehouse_service.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseResponse {

    private Long id;
    private String warehouseCode;
    private String name;
    private String location;
    private Integer capacity;
    private Integer availableCapacity;
}