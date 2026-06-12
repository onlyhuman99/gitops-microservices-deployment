package com.parth.example.shipment_service.controller;

import com.parth.example.shipment_service.entity.Shipment;
import com.parth.example.shipment_service.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipments")
@RequiredArgsConstructor
public class ShipmentController {

    private final ShipmentService service;

    @PostMapping
    public Shipment createShipment(
            @RequestBody Shipment shipment) {

        return service.createShipment(shipment);
    }

    @GetMapping
    public List<Shipment> getAllShipments() {
        return service.getAllShipments();
    }
}