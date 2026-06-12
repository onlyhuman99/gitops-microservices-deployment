package com.parth.example.shipment_service.service;

import com.parth.example.shipment_service.entity.Shipment;
import com.parth.example.shipment_service.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipmentService {

    private final ShipmentRepository repository;

    public Shipment createShipment(Shipment shipment) {
        return repository.save(shipment);
    }

    public List<Shipment> getAllShipments() {
        return repository.findAll();
    }
}