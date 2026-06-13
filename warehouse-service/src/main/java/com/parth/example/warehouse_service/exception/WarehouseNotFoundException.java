package com.parth.example.warehouse_service.exception;

public class WarehouseNotFoundException
        extends RuntimeException {

    public WarehouseNotFoundException(Long id) {
        super("Warehouse not found with id: " + id);
    }
}