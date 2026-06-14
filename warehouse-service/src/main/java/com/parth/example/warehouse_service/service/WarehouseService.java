package com.parth.example.warehouse_service.service;

import com.parth.example.warehouse_service.dto.WarehouseRequest;
import com.parth.example.warehouse_service.entity.ShipmentAssignment;
import com.parth.example.warehouse_service.entity.Warehouse;
import com.parth.example.warehouse_service.exception.WarehouseCapacityExceededException;
import com.parth.example.warehouse_service.exception.WarehouseNotFoundException;
import com.parth.example.warehouse_service.repository.ShipmentAssignmentRepository;
import com.parth.example.warehouse_service.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseService {

    private final WarehouseRepository repository;
    private final ShipmentAssignmentRepository assignmentRepository;

    public Warehouse createWarehouse(
            WarehouseRequest request) {

        Warehouse warehouse =
                Warehouse.builder()
                        .warehouseCode(request.getWarehouseCode())
                        .name(request.getName())
                        .location(request.getLocation())
                        .capacity(request.getCapacity())
                        .availableCapacity(
                                request.getAvailableCapacity())
                        .build();

        return repository.save(warehouse);
    }

    public List<Warehouse> getAllWarehouses() {
        return repository.findAll();
    }

    public Warehouse getWarehouseById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new WarehouseNotFoundException(id));
    }

    public Warehouse updateWarehouse(
            Long id,
            WarehouseRequest request) {

        Warehouse warehouse =
                getWarehouseById(id);

        warehouse.setWarehouseCode(
                request.getWarehouseCode());

        warehouse.setName(
                request.getName());

        warehouse.setLocation(
                request.getLocation());

        warehouse.setCapacity(
                request.getCapacity());

        warehouse.setAvailableCapacity(
                request.getAvailableCapacity());

        return repository.save(warehouse);
    }

    public void deleteWarehouse(Long id) {

        Warehouse warehouse =
                getWarehouseById(id);

        repository.delete(warehouse);
    }
    public Warehouse assignShipment(
        Long shipmentId,
        Long warehouseId) {

    Warehouse warehouse =
            getWarehouseById(warehouseId);

    if (warehouse.getAvailableCapacity() <= 0) {
        throw new WarehouseCapacityExceededException();
    }

    ShipmentAssignment assignment =
            ShipmentAssignment.builder()
                    .shipmentId(shipmentId)
                    .warehouseId(warehouseId)
                    .build();

    assignmentRepository.save(assignment);

    warehouse.setAvailableCapacity(
            warehouse.getAvailableCapacity() - 1);

    return repository.save(warehouse);
    }
}