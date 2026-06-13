package com.parth.example.warehouse_service.exception;

public class WarehouseCapacityExceededException
        extends RuntimeException {

    public WarehouseCapacityExceededException() {
        super("Warehouse has no available capacity");
    }
}