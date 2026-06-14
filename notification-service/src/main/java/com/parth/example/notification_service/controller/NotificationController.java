package com.parth.example.notification_service.controller;

import com.parth.example.notification_service.dto.NotificationRequest;
import com.parth.example.notification_service.entity.Notification;
import com.parth.example.notification_service.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService service;

    @PostMapping("/send")
    public Notification sendNotification(
            @Valid
            @RequestBody NotificationRequest request) {

        return service.sendNotification(
                request);
    }

    @GetMapping
    public List<Notification>
    getAllNotifications() {

        return service.getAllNotifications();
    }

    @GetMapping("/shipment/{shipmentId}")
    public List<Notification>
    getNotificationsByShipment(
            @PathVariable Long shipmentId) {

        return service.getNotificationsByShipment(
                shipmentId);
    }
}