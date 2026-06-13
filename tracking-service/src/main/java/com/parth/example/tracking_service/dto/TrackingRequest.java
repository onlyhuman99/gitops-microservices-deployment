package com.parth.example.tracking_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrackingRequest {

    @NotNull
    private Long shipmentId;

    @NotNull
    private String currentLocation;
}