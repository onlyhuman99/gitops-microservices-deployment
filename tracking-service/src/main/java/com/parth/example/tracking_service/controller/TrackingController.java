package com.parth.example.tracking_service.controller;

import com.parth.example.tracking_service.dto.TrackingRequest;
import com.parth.example.tracking_service.entity.TrackingRecord;
import com.parth.example.tracking_service.service.TrackingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tracking")
@RequiredArgsConstructor
public class TrackingController {

    private final TrackingService service;

    @PostMapping("/update")
    public TrackingRecord updateLocation(
            @Valid
            @RequestBody TrackingRequest request) {

        TrackingRecord record =
                TrackingRecord.builder()
                        .shipmentId(
                                request.getShipmentId())
                        .currentLocation(
                                request.getCurrentLocation())
                        .build();

        return service.updateLocation(record);
    }

    @GetMapping("/{shipmentId}")
    public List<TrackingRecord>
    getTrackingHistory(
            @PathVariable Long shipmentId) {

        return service.getTrackingHistory(
                shipmentId);
    }
}