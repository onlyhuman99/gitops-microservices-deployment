package com.parth.example.tracking_service.service;

import com.parth.example.tracking_service.entity.TrackingRecord;
import com.parth.example.tracking_service.exception.TrackingRecordNotFoundException;
import com.parth.example.tracking_service.repository.TrackingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrackingService {

    private final TrackingRepository repository;

    public TrackingRecord updateLocation(
            TrackingRecord record) {

        return repository.save(record);
    }

    public List<TrackingRecord>
    getTrackingHistory(Long shipmentId) {

        List<TrackingRecord> records =
                repository.findByShipmentId(shipmentId);

        if (records.isEmpty()) {
            throw new TrackingRecordNotFoundException(
                    shipmentId);
        }

        return records;
    }
}