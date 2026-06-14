package com.parth.example.warehouse_service.repository;

import com.parth.example.warehouse_service.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository
        extends JpaRepository<Warehouse, Long> {
}