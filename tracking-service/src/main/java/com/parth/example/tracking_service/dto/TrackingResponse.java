package com.parth.example.tracking_service.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrackingResponse {

    private Long id;
    private Long shipmentId;
    private String currentLocation;
    private LocalDateTime timestamp;
}