package com.parth.example.notification_service.service;

import com.parth.example.notification_service.dto.NotificationRequest;
import com.parth.example.notification_service.entity.Notification;
import com.parth.example.notification_service.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository repository;

    public Notification sendNotification(
            NotificationRequest request) {

        Notification notification =
                Notification.builder()
                        .shipmentId(request.getShipmentId())
                        .message(request.getMessage())
                        .type(request.getType())
                        .build();

        Notification saved =
                repository.save(notification);

        System.out.println(
                "NOTIFICATION SENT: "
                        + saved.getMessage());

        return saved;
    }

    public List<Notification> getAllNotifications() {
        return repository.findAll();
    }

    public List<Notification>
    getNotificationsByShipment(
            Long shipmentId) {

        return repository.findByShipmentId(
                shipmentId);
    }
}