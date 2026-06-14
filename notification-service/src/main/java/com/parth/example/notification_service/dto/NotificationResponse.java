package com.parth.example.notification_service.dto;

import com.parth.example.notification_service.entity.NotificationType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {

    private Long id;
    private Long shipmentId;
    private String message;
    private NotificationType type;
    private LocalDateTime timestamp;
}