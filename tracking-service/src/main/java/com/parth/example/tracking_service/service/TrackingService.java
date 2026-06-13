package com.parth.example.tracking_service.service;

import com.parth.example.tracking_service.entity.TrackingRecord;
import com.parth.example.tracking_service.repository.TrackingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrackingService {

    private final TrackingRepository repository;

    public TrackingRecord updateLocation(TrackingRecord record) {
        return repository.save(record);
    }

    public List<TrackingRecord> getTrackingHistory(Long shipmentId) {
        return repository.findByShipmentId(shipmentId);
    }
}