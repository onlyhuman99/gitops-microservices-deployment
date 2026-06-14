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
    public Shipment getShipmentById(Long id) {
    return repository.findById(id)
            .orElseThrow(() ->
                    new RuntimeException("Shipment not found"));
}

public Shipment updateShipment(Long id,
                               Shipment updatedShipment) {

    Shipment shipment = getShipmentById(id);

    shipment.setTrackingNumber(updatedShipment.getTrackingNumber());
    shipment.setSource(updatedShipment.getSource());
    shipment.setDestination(updatedShipment.getDestination());
    shipment.setStatus(updatedShipment.getStatus());

    return repository.save(shipment);
}

public void deleteShipment(Long id) {
    repository.deleteById(id);
}
}