package com.parth.example.shipment_service.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

@Service
public class WarehouseClient {

    @Value("${warehouse.service.url}")
    private String warehouseServiceUrl;
    private final RestClient restClient;

    public WarehouseClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public void autoAssignWarehouse(Long shipmentId) {

        restClient.post()
                .uri(warehouseServiceUrl + "/api/warehouses/auto-assign")
                .body(Map.of(
                        "shipmentId", shipmentId
                ))
                .retrieve()
                .toBodilessEntity();
    }
}