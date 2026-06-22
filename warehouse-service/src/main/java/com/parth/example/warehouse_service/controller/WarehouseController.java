package com.parth.example.warehouse_service.controller;

import com.parth.example.warehouse_service.dto.AssignShipmentRequest;
import com.parth.example.warehouse_service.dto.AutoAssignShipmentRequest;
import com.parth.example.warehouse_service.dto.WarehouseRequest;
import com.parth.example.warehouse_service.entity.ShipmentAssignment;
import com.parth.example.warehouse_service.entity.Warehouse;
import com.parth.example.warehouse_service.repository.ShipmentAssignmentRepository;
import com.parth.example.warehouse_service.service.WarehouseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
@RequiredArgsConstructor
public class WarehouseController {

    private final WarehouseService service;
    private final ShipmentAssignmentRepository assignmentRepository;
    
    @PostMapping
    public Warehouse createWarehouse(
            @Valid
            @RequestBody WarehouseRequest request) {

        return service.createWarehouse(request);
    }

    @GetMapping
    public List<Warehouse> getAllWarehouses() {
        return service.getAllWarehouses();
    }

    @GetMapping("/{id}")
    public Warehouse getWarehouseById(
            @PathVariable Long id) {

        return service.getWarehouseById(id);
    }

    @PutMapping("/{id}")
    public Warehouse updateWarehouse(
            @PathVariable Long id,
            @Valid
            @RequestBody WarehouseRequest request) {

        return service.updateWarehouse(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteWarehouse(
            @PathVariable Long id) {

        service.deleteWarehouse(id);
    }
    
    @PostMapping("/assign")
    public Warehouse assignShipment(
        @Valid
        @RequestBody AssignShipmentRequest request) {

    return service.assignShipment(
            request.getShipmentId(),
            request.getWarehouseId());
    }
 
    @PostMapping("/auto-assign")
    public Warehouse autoAssignShipment(
        @Valid
        @RequestBody AutoAssignShipmentRequest request) {
        return service.autoAssignShipment(
                request.getShipmentId());
    }

    @GetMapping("/{warehouseId}/assignments")
    public List<ShipmentAssignment>
    getAssignments(
            @PathVariable Long warehouseId) {

        return assignmentRepository.findByWarehouseId(warehouseId);
    }
}