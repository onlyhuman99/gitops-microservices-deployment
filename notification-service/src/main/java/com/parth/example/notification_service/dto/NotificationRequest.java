package com.parth.example.notification_service.dto;

import com.parth.example.notification_service.entity.NotificationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest {

    @NotNull
    private Long shipmentId;

    @NotBlank
    private String message;

    @NotNull
    private NotificationType type;
}