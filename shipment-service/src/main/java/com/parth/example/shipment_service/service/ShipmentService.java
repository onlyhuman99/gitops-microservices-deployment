package com.parth.example.shipment_service.service;

import com.parth.example.shipment_service.client.NotificationClient;
import com.parth.example.shipment_service.client.TrackingClient;
import com.parth.example.shipment_service.client.WarehouseClient;
import com.parth.example.shipment_service.entity.Shipment;
import com.parth.example.shipment_service.repository.ShipmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentService {
    
    private final TrackingClient trackingClient;
    private final WarehouseClient warehouseClient;
    private final NotificationClient notificationClient;

    public ShipmentService(
        ShipmentRepository repository,
        TrackingClient trackingClient,
        WarehouseClient warehouseClient,
        NotificationClient notificationClient) {

    this.repository = repository;
    this.trackingClient = trackingClient;
    this.warehouseClient = warehouseClient;
    this.notificationClient = notificationClient;
}

    private final ShipmentRepository repository;
    
    public Shipment createShipment(Shipment shipment) 
    {
        Shipment savedShipment = repository.save(shipment);

        trackingClient.createTracking(savedShipment.getId());
        warehouseClient.autoAssignWarehouse(savedShipment.getId());
        notificationClient.sendNotification(savedShipment.getId());

        return savedShipment;
    }

    public List<Shipment> getAllShipments() {
        return repository.findAll();
    }

    public Shipment getShipmentById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Shipment not found"));
    }

    public Shipment updateShipment(Long id, Shipment updatedShipment) {

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