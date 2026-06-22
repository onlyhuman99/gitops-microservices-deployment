package com.parth.example.shipment_service.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

@Service
public class NotificationClient {

    @Value("${notification.service.url}")
    private String notificationServiceUrl;
    private final RestClient restClient;

    public NotificationClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public void sendNotification(Long shipmentId) {

        restClient.post()
                .uri(notificationServiceUrl + "/api/notifications/send")
                .body(Map.of(
                        "shipmentId", shipmentId,
                        "message", "Shipment Created",
                        "type", "EMAIL"
                ))
                .retrieve()
                .toBodilessEntity();
    }
}