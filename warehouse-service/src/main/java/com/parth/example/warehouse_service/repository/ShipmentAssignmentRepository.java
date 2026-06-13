package com.parth.example.warehouse_service.repository;

import com.parth.example.warehouse_service.entity.ShipmentAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShipmentAssignmentRepository
        extends JpaRepository<ShipmentAssignment, Long> {

    List<ShipmentAssignment>
    findByWarehouseId(Long warehouseId);
}