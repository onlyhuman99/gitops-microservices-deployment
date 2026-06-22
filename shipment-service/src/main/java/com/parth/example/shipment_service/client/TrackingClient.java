package com.parth.example.shipment_service.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import java.util.Map;


@Service
public class TrackingClient {
    @Value("${tracking.service.url}")
    private String trackingServiceUrl;
    private final RestClient restClient;
    
    public TrackingClient(RestClient restClient) {
        this.restClient = restClient;
    }
    
    
    public void createTracking(Long shipmentId) {
        
        restClient.post()
        .uri(trackingServiceUrl + "/api/tracking/update")
        .body(Map.of(
            "shipmentId", shipmentId,
            "currentLocation", "Origin Warehouse"
        ))
        .retrieve()
        .toBodilessEntity();
    }
}