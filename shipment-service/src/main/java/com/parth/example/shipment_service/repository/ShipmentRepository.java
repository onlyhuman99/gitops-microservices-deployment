package com.parth.example.shipment_service.repository;

import com.parth.example.shipment_service.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository
        extends JpaRepository<Shipment, Long> {
}